# DynamicShop 開發 API 手冊

本文檔說明如何在其他插件中使用 DynamicShop 的公開 API，並簡要介紹主要類別與其依賴關係。

## 1. 專案概觀
DynamicShop 是一套基於 Spigot 的動態商店系統。主類別 `DynamicShop` 於啟動時初始化各模組並掛載必要的事件與指令【F:src/main/java/me/sat7/dynamicshop/DynamicShop.java†L216-L303】。提供的 API 由 `DynaShopAPI` 類別以靜態方法形式釋出，方便其他插件呼叫【F:src/main/java/me/sat7/dynamicshop/DynaShopAPI.java†L22-L31】。

專案主要分為以下模組：

- **commands**：處理 `/ds`、`/shop`、`/sell` 等指令邏輯。
- **guis**：以 `UIManager` 管理多種 GUI 介面，如 `Shop`、`ItemTrade`、`ShopSettings` 等。
- **transactions**：購買與販售邏輯 (`Buy`、`Sell`、`Calc`)【F:src/main/java/me/sat7/dynamicshop/transactions/Calc.java†L1-L30】。
- **utilities**：輔助工具與設定處理，例如 `ShopUtil`、`ConfigUtil`、`UserUtil` 等。
- **events**：自訂事件與監聽，例如 `ShopBuySellEvent`【F:src/main/java/me/sat7/dynamicshop/events/ShopBuySellEvent.java†L1-L42】。
- **files**：`CustomConfig` 用於存取與維護各種 YAML 檔案【F:src/main/java/me/sat7/dynamicshop/files/CustomConfig.java†L1-L87】。

## 2. 依賴與外掛
DynamicShop 依賴 Vault 做為經濟系統，以及可選的 PlaceholderAPI、Jobs、PlayerPoints、LocaleLib 等外掛。這些在 `plugin.yml` 與 `pom.xml` 中可以看到【F:src/main/resources/plugin.yml†L1-L8】【F:pom.xml†L55-L117】。

## 3. 主要類別說明
### 3.1 DynamicShop
`DynamicShop` 繼承自 `JavaPlugin`，於 `onEnable()` 中初始化指令、事件與設定檔，並處理與 Vault 及其他外掛的連結【F:src/main/java/me/sat7/dynamicshop/DynamicShop.java†L216-L303】【F:src/main/java/me/sat7/dynamicshop/DynamicShop.java†L344-L429】。

### 3.2 DynaShopAPI
此類提供對外 API，所有方法均為靜態。常用方法包含：

- `openShopGui(Player, String, int)`：開啟指定商店的 GUI【F:src/main/java/me/sat7/dynamicshop/DynaShopAPI.java†L33-L63】。
- `openItemTradeGui(Player, String, String)`：開啟單一交易介面【F:src/main/java/me/sat7/dynamicshop/DynaShopAPI.java†L77-L108】。
- `getBuyPrice(String, ItemStack)` / `getSellPrice(String, ItemStack)`：取得物品買入或賣出的即時價格【F:src/main/java/me/sat7/dynamicshop/DynaShopAPI.java†L205-L278】。
- `getStock(String, ItemStack)`：取得庫存量【F:src/main/java/me/sat7/dynamicshop/DynaShopAPI.java†L320-L344】。
- `QuickSell(Player, ItemStack)`：快速販賣物品，回傳獲得金額【F:src/main/java/me/sat7/dynamicshop/DynaShopAPI.java†L373-L415】。

若需偵聽買賣事件，可監聽 `ShopBuySellEvent`【F:src/main/java/me/sat7/dynamicshop/events/ShopBuySellEvent.java†L1-L42】。

### 3.3 ShopUtil
`ShopUtil` 為操作商店資料的核心工具，包含重新載入商店、尋找空槽位與管理商店帳戶等功能【F:src/main/java/me/sat7/dynamicshop/utilities/ShopUtil.java†L1-L45】【F:src/main/java/me/sat7/dynamicshop/utilities/ShopUtil.java†L160-L218】。

### 3.4 Transaction 類別
`Buy`、`Sell` 與 `Calc` 共同負責交易流程：

- `Calc` 計算動態價格與稅率【F:src/main/java/me/sat7/dynamicshop/transactions/Calc.java†L1-L69】。
- `Buy.buy(...)` 對玩家扣款並給予物品【F:src/main/java/me/sat7/dynamicshop/transactions/Buy.java†L27-L109】。
- `Sell.quickSellItem(...)` 協助快速販賣並將收入返還玩家【F:src/main/java/me/sat7/dynamicshop/transactions/Sell.java†L27-L95】。

## 4. 使用範例
在其他插件中呼叫 DynamicShop API 的簡易範例如下：

```java
import me.sat7.dynamicshop.DynaShopAPI;
...
// 打開名為 "SampleShop" 的第一頁商店
DynaShopAPI.openShopGui(player, "SampleShop", 1);
```

## 5. 建議閱讀順序
1. 從 `DynaShopAPI` 開始瞭解可用的外部方法。
2. 參考 `ShopUtil` 了解商店資料結構與操作方式。
3. 若需客製化事件或交易邏輯，可深入 `transactions` 與 `events` 套件。

如需更詳細的設定與進階功能，請參考原始碼或官方文件。歡迎依照 MIT 授權條款自由使用與修改本專案。
