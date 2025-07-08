package me.sat7.dynamicshop.utilities;

import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;

import me.sat7.dynamicshop.DynamicShop;
import me.sat7.dynamicshop.constants.Constants;
import me.sat7.dynamicshop.files.CustomConfig;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class LangUtil
{
    public static CustomConfig ccLang = new CustomConfig();

    private LangUtil()
    {

    }

    public static void setupLangFile(String lang)
    {
        // 한국어
        ko_KR();

        // 영어
        en_US();

        // 繁體中文
        zh_TW();

        if (lang == null) lang = "en-US";

        if (!lang.equals("en-US") && !lang.equals("ko-KR") && !lang.equalsIgnoreCase("zh-TW"))
        {
            ConfigurationSection conf = ccLang.get().getConfigurationSection("");

            ccLang.setup("Lang_V3_" + lang, null);

            for (String s : conf.getKeys(true))
            {
                if (!ccLang.get().contains(s))
                {
                    DynamicShop.console.sendMessage(Constants.DYNAMIC_SHOP_PREFIX + "String Key " + s + " added");
                    ccLang.get().addDefault(s, conf.get(s));
                }
            }
        } else
        {
            ccLang.setup("Lang_V3_" + lang, null);
        }

        ccLang.get().options().copyDefaults(true);
        ccLang.save();

        ReloadNumberFormat();
    }

    private static void ko_KR()
    {
        ccLang.setup("Lang_V3_ko-KR", null);

        ccLang.get().addDefault("START_PAGE.EDITOR_TITLE", "§3아이콘 편집");
        ccLang.get().addDefault("START_PAGE.EDIT_NAME", "§f이름 바꾸기");
        ccLang.get().addDefault("START_PAGE.EDIT_LORE", "§f설명 바꾸기");
        ccLang.get().addDefault("START_PAGE.EDIT_ICON", "§f아이콘 바꾸기");
        ccLang.get().addDefault("START_PAGE.EDIT_ACTION", "§f실행 명령어 바꾸기");
        ccLang.get().addDefault("START_PAGE.SHOP_SHORTCUT", "§f상점으로 가는 버튼 만들기");
        ccLang.get().addDefault("START_PAGE.CREATE_DECO", "§f장식 버튼 만들기");
        ccLang.get().addDefault("START_PAGE.ENTER_SHOP_NAME", "상점 이름을 입력하세요.");
        ccLang.get().addDefault("START_PAGE.DEFAULT_SHOP_LORE", "§f§n클릭: 상점으로 가기");
        ccLang.get().addDefault("START_PAGE.ITEM_MOVE_LORE", "§e우클릭: 이동");
        ccLang.get().addDefault("START_PAGE.ITEM_COPY_LORE", "§e우클릭: 복사");
        ccLang.get().addDefault("START_PAGE.ITEM_REMOVE_LORE", "§eShift 좌클릭: 삭제");
        ccLang.get().addDefault("START_PAGE.ITEM_EDIT_LORE", "§eShift우클릭: 편집");
        ccLang.get().addDefault("START_PAGE.REMOVE", "§f제거");
        ccLang.get().addDefault("START_PAGE.REMOVE_LORE", "§f이 버튼을 시작페이지에서 제거합니다.");
        ccLang.get().addDefault("START_PAGE.ENTER_NAME", "버튼의 새 이름을 입력하세요.");
        ccLang.get().addDefault("START_PAGE.ENTER_LORE", "버튼의 새 설명을 입력하세요.");
        ccLang.get().addDefault("START_PAGE.ENTER_ICON", "버튼의 아이콘으로 사용할 아이탬 이름을 입력하세요. (영문. 대소문자 구분없음)");
        ccLang.get().addDefault("START_PAGE.ENTER_ACTION", "명령어를 '/' 제외하고 입력하세요. 버튼을 눌렀을때 이 명령어가 실행됩니다.");
        ccLang.get().addDefault("START_PAGE.ENTER_COLOR", "장식 버튼의 색상을 입력하세요. (영문)");
        ccLang.get().addDefault("START_PAGE.SHOP_LIST_TITLE", "§3상점 목록");
        ccLang.get().addDefault("START_PAGE.SHOP_LIST.PAGE_TITLE", "§f{curPage}/{maxPage} 페이지");
        ccLang.get().addDefault("START_PAGE.SHOP_LIST.PAGE_LORE", "§e좌클릭: 이전 페이지\n§e우클릭: 다음 페이지");

        ccLang.get().addDefault("COLOR_PICKER_TITLE", "§3색상 선택");

        ccLang.get().addDefault("SHOP.TRADE_LORE", "§f§n클릭: 거래");
        ccLang.get().addDefault("SHOP.BUY_PRICE", "§f구매: {num}");
        ccLang.get().addDefault("SHOP.SELL_PRICE", "§f판매: {num}");
        ccLang.get().addDefault("SHOP.BUY_PRICE_DISCOUNTED", "§f구매: §8§m{num}§r §a{num2}");
        ccLang.get().addDefault("SHOP.SELL_PRICE_DISCOUNTED", "§f판매: §8§m{num}§r §c{num2}");
        ccLang.get().addDefault("SHOP.BUY_PRICE_PP", "§f구매: {num}PP");
        ccLang.get().addDefault("SHOP.SELL_PRICE_PP", "§f판매: {num}PP");
        ccLang.get().addDefault("SHOP.BUY_PRICE_EXP", "§f구매: {num}Exp");
        ccLang.get().addDefault("SHOP.SELL_PRICE_EXP", "§f판매: {num}Exp");
        ccLang.get().addDefault("SHOP.BUY_PRICE_DISCOUNTED_EXP", "§f구매: §8§m{num}Exp§r §a{num2}Exp");
        ccLang.get().addDefault("SHOP.SELL_PRICE_DISCOUNTED_EXP", "§f판매: §8§m{num}Exp§r §c{num2}Exp");
        ccLang.get().addDefault("SHOP.BUY_PRICE_DISCOUNTED_PP", "§f구매: §8§m{num}PP§r §a{num2}PP");
        ccLang.get().addDefault("SHOP.SELL_PRICE_DISCOUNTED_PP", "§f판매: §8§m{num}PP§r §c{num2}PP");
        ccLang.get().addDefault("SHOP.BUY_PRICE_JP", "§f구매: {num}JP");
        ccLang.get().addDefault("SHOP.SELL_PRICE_JP", "§f판매: {num}JP");
        ccLang.get().addDefault("SHOP.BUY_PRICE_DISCOUNTED_JP", "§f구매: §8§m{num}JP§r §a{num2}JP");
        ccLang.get().addDefault("SHOP.SELL_PRICE_DISCOUNTED_JP", "§f판매: §8§m{num}JP§r §c{num2}JP");
        ccLang.get().addDefault("SHOP.STOCK", "§8재고: {num}");
        ccLang.get().addDefault("SHOP.STOCK_2", "§8재고: {stock}/{max_stock}");
        ccLang.get().addDefault("SHOP.INF_STOCK", "§8무한");
        ccLang.get().addDefault("SHOP.STATIC_PRICE", "§8[고정 가격]");
        ccLang.get().addDefault("SHOP.STACKS", "§8{num} 스택");
        ccLang.get().addDefault("SHOP.ITEM_MOVE_LORE", "§e우클릭: 이동");
        ccLang.get().addDefault("SHOP.ITEM_COPY_LORE", "§e우클릭: 복사");
        ccLang.get().addDefault("SHOP.ITEM_EDIT_LORE", "§eShift우클릭: 편집");
        ccLang.get().addDefault("SHOP.DECO_DELETE_LORE", "§eShift우클릭: 삭제");
        ccLang.get().addDefault("SHOP.PAGE_TITLE", "§f{curPage}/{maxPage} 페이지");
        ccLang.get().addDefault("SHOP.PAGE_LORE_V2", "§f§n좌클릭: 이전 페이지\n§f§n우클릭: 다음 페이지\n§7인벤토리에서 아이템을 클릭하면 \n§7그 아이템이 있는 페이지로 이동합니다.");
        ccLang.get().addDefault("SHOP.GO_TO_PAGE_EDITOR", "§eShift+우: 페이지 에디터");
        ccLang.get().addDefault("SHOP.ITEM_MOVE_SELECTED", "아이탬 선택됨. 비어있는 칸을 우클릭하면 이동합니다.");
        ccLang.get().addDefault("SHOP.PERMISSION", "§f퍼미션:");
        ccLang.get().addDefault("SHOP.PERMISSION_ITEM", "§7 - {permission}");
        ccLang.get().addDefault("SHOP.FLAGS", "§e플래그:");
        ccLang.get().addDefault("SHOP.FLAGS_ITEM", "§e - {flag}");
        ccLang.get().addDefault("SHOP.SHOP_BAL_INF", "§f상점 계좌 무제한");
        ccLang.get().addDefault("SHOP.SHOP_BAL", "§f상점 계좌 잔액");
        ccLang.get().addDefault("SHOP.SHOP_LOCATION", "§f상점 위치: x {x}, y {y}, z {z}");
        ccLang.get().addDefault("SHOP.SHOP_LOCATION_B", "§f상점 위치: ");
        ccLang.get().addDefault("SHOP.SHOP_INFO_DASH", "§7 - ");
        ccLang.get().addDefault("SHOP.DISABLED", "§c비활성§8|§f");
        ccLang.get().addDefault("SHOP.INCOMPLETE_DATA", "불완전한 데이터");
        ccLang.get().addDefault("SHOP.INCOMPLETE_DATA_Lore", "이 아이템은 어드민이 아닌\n유저에게는 보이지 않습니다.\nIndex: ");
        ccLang.get().addDefault("SHOP.TRADE_LIMIT_BUY", "§a구매량 제한 : {num} 남음");
        ccLang.get().addDefault("SHOP.TRADE_LIMIT_SELL", "§a판매량 제한 : {num} 남음");
        ccLang.get().addDefault("SHOP.TRADE_LIMIT_TIMER", "§a다음 리셋: {time}");
        ccLang.get().addDefault("SHOP.CLICK_TO_ADD", "§e클릭: 추가");

        ccLang.get().addDefault("SHOP_SETTING_TITLE", "§3상점 설정");
        ccLang.get().addDefault("SHOP_SETTING.LOG_TOGGLE_LORE", "§e우클릭: 로그 뷰어");
        ccLang.get().addDefault("SHOP_SETTING.LOG_PRINT_CONSOLE", "§f로그를 콘솔에 출력");
        ccLang.get().addDefault("SHOP_SETTING.LOG_PRINT_ADMIN", "§f로그를 어드민에게 출력");
        ccLang.get().addDefault("SHOP_SETTING.MAX_PAGE", "§f최대 페이지");
        ccLang.get().addDefault("SHOP_SETTING.MAX_PAGE_LORE", "§f상점의 최대 페이지를 설정합니다");
        ccLang.get().addDefault("SHOP_SETTING.BACKGROUND", "§f배경 색상");
        ccLang.get().addDefault("SHOP_SETTING.BACKGROUND_LORE", "§e클릭: 변경");
        ccLang.get().addDefault("SHOP_SETTING.L_R_SHIFT", "§e좌: -1 우: +1 Shift: x5");
        ccLang.get().addDefault("SHOP_SETTING.FLAG", "§f플래그");
        ccLang.get().addDefault("SHOP_SETTING.SHOP_SETTINGS_LORE", "§e우클릭: 상점 편집");
        ccLang.get().addDefault("SHOP_SETTING.SIGN_SHOP_LORE", "§f표지판을 통해서만 접근할 수 있습니다.");
        ccLang.get().addDefault("SHOP_SETTING.LOCAL_SHOP_LORE", "§f실제 상점 위치를 방문해야 합니다.\n§f상점의 위치를 설정해야만 합니다.");
        ccLang.get().addDefault("SHOP_SETTING.DELIVERY_CHARGE_LORE", "§f배달비를 지불하고 localshop에서 원격으로 거래합니다.");
        ccLang.get().addDefault("SHOP_SETTING.SELECTED", "§2선택됨");
        ccLang.get().addDefault("SHOP_SETTING.CURRENCY", "§f화폐 유형: ");
        ccLang.get().addDefault("SHOP_SETTING.VAULT_LORE", "§f기본값 입니다.");
        ccLang.get().addDefault("SHOP_SETTING.EXP_LORE", "§f플레이어의 경험치로 거래합니다.");
        ccLang.get().addDefault("SHOP_SETTING.JOB_POINT_LORE", "§fJobs 플러그인의 job point로 거래합니다.");
        ccLang.get().addDefault("SHOP_SETTING.PLAYER_POINT_LORE", "§fPlayerPoint 플러그인의 point로 거래합니다.");
        ccLang.get().addDefault("SHOP_SETTING.SHOW_VALUE_CHANGE_LORE", "§f가격 변화량을 표시합니다.");
        ccLang.get().addDefault("SHOP_SETTING.HIDE_STOCK", "§f재고 수량 표시를 숨깁니다.");
        ccLang.get().addDefault("SHOP_SETTING.HIDE_PRICING_TYPE", "§f가격 유형 표기를 숨깁니다.");
        ccLang.get().addDefault("SHOP_SETTING.HIDE_SHOP_BALANCE", "§f상점 계좌 잔액을 숨깁니다.");
        ccLang.get().addDefault("SHOP_SETTING.SHOW_MAX_STOCK", "§f재고 상한을 표시합니다.");
        ccLang.get().addDefault("SHOP_SETTING.HIDDEN_IN_COMMAND", "§f명령어 자동완성시 이 상점을 표시하지 않습니다.");
        ccLang.get().addDefault("SHOP_SETTING.INTEGER_ONLY", "§f구매 가격이 올림 처리됩니다.\n§f판매 가격은 내림 처리됩니다.");
        ccLang.get().addDefault("SHOP_SETTING.PERMISSION", "§f퍼미션");
        ccLang.get().addDefault("SHOP_SETTING.STATE", "§f상태");
        ccLang.get().addDefault("SHOP_SETTING.STATE_ENABLE", "§a활성");
        ccLang.get().addDefault("SHOP_SETTING.STATE_DISABLE", "§c비활성");
        ccLang.get().addDefault("SHOP_SETTING.ROTATION_EDITOR", "§f로테이션: ");
        ccLang.get().addDefault("SHOP_SETTING.ROTATION_EDITOR_LORE", "§e클릭: 로테이션 에디터");
        ccLang.get().addDefault("SHOP_SETTING.COMMAND_TOGGLE", "§f커맨드 실행");
        ccLang.get().addDefault("SHOP_SETTING.COMMAND_TOGGLE_LORE", "§f상점 거래 발생시 서버 명령어를 실행합니다.");
        ccLang.get().addDefault("SHOP_SETTING.COMMAND_SELL", "§f판매시 명령어");
        ccLang.get().addDefault("SHOP_SETTING.COMMAND_BUY", "§f구매시 명령어");
        ccLang.get().addDefault("SHOP_SETTING.COMMAND_LORE1", "§e좌클릭: 설정");
        ccLang.get().addDefault("SHOP_SETTING.COMMAND_LORE3", "§e씨프트 우클릭: 마지막 항목 삭제");
        ccLang.get().addDefault("SHOP_SETTING.TRADE_UI", "§f거래 UI 설정");
        ccLang.get().addDefault("SHOP_SETTING.TRADE_UI_LORE_2", "§e좌클릭: 편집\n§e우클릭: 초기화");

        ccLang.get().addDefault("ROTATION_EDITOR_TITLE", "§3로테이션 에디터");
        ccLang.get().addDefault("ROTATION_EDITOR.ENABLED", "§a로테이션 켜짐");
        ccLang.get().addDefault("ROTATION_EDITOR.DISABLED", "§c로테이션 꺼짐");
        ccLang.get().addDefault("ROTATION_EDITOR.CLICK_TO_ENABLE", "§e좌클릭: 로테이션 켜기");
        ccLang.get().addDefault("ROTATION_EDITOR.CLICK_TO_DISABLE", "§e좌클릭: 로테이션 끄기");
        ccLang.get().addDefault("ROTATION_EDITOR.CURRENT_TIME", "§f현재 시간: ");
        ccLang.get().addDefault("ROTATION_EDITOR.CURRENTLY_IN_USE", "§a[현재 적용중]");
        ccLang.get().addDefault("ROTATION_EDITOR.NEXT_ROTATION", "§f다음 로테이션: ");
        ccLang.get().addDefault("ROTATION_EDITOR.PERIOD", "§f로테이션 주기");
        ccLang.get().addDefault("ROTATION_EDITOR.PERIOD_LORE_V2", "§e좌클릭: -1시간, 우클릭: +1시간, 씨프트: x10");
        ccLang.get().addDefault("ROTATION_EDITOR.TIMER", "§f타이머 조정");
        ccLang.get().addDefault("ROTATION_EDITOR.TIMER_LORE_V2", "§e좌클릭: -10분, 우클릭: +10분, 씨프트: x6");
        ccLang.get().addDefault("ROTATION_EDITOR.HOUR", "§f{0}시간");
        ccLang.get().addDefault("ROTATION_EDITOR.UNSAVED_CHANGES", "§c§o저장되지 않은 변경사항 있음");
        ccLang.get().addDefault("ROTATION_EDITOR.APPLY_CHANGES", "§f변경사항 적용");
        ccLang.get().addDefault("ROTATION_EDITOR.APPLY_CHANGES_LORE", "§e좌클릭: 변경사항 적용\n§e우클릭: 변경사항 초기화");
        ccLang.get().addDefault("ROTATION_EDITOR.APPLY_ROTATION", "§e좌클릭: 지금 적용");
        ccLang.get().addDefault("ROTATION_EDITOR.OPEN", "§e좌클릭: 열기");
        ccLang.get().addDefault("ROTATION_EDITOR.CREATE", "§e좌클릭: 빈 로테이션 만들기");
        ccLang.get().addDefault("ROTATION_EDITOR.COPY_AS_NEW", "§e우클릭: 현재 상점 복사하여 만들기");
        ccLang.get().addDefault("ROTATION_EDITOR.DELETE", "§e씨프트 우클릭: §c삭제");
        ccLang.get().addDefault("ROTATION_EDITOR.REAPPLY", "§e씨프트 좌클릭: 재적용");
        ccLang.get().addDefault("ROTATION_EDITOR.MOVE", "§e우클릭: 이동");

        ccLang.get().addDefault("ITEM_SETTING_TITLE", "§3아이탬 셋팅");
        ccLang.get().addDefault("ITEM_SETTING.VALUE_BUY", "§f구매가치: ");
        ccLang.get().addDefault("ITEM_SETTING.VALUE_SELL", "§f판매가치: ");
        ccLang.get().addDefault("ITEM_SETTING.PRICE", "§f구매: ");
        ccLang.get().addDefault("ITEM_SETTING.SELL_PRICE", "§f판매: ");
        ccLang.get().addDefault("ITEM_SETTING.PRICE_MIN", "§f최소 가격: ");
        ccLang.get().addDefault("ITEM_SETTING.PRICE_MAX", "§f최대 가격: ");
        ccLang.get().addDefault("ITEM_SETTING.MEDIAN", "§f중앙값: ");
        ccLang.get().addDefault("ITEM_SETTING.STOCK", "§f재고: ");
        ccLang.get().addDefault("ITEM_SETTING.MAX_STOCK", "§f재고 상한: ");
        ccLang.get().addDefault("ITEM_SETTING.MAX_STOCK_LORE", "§f재고량이 이보다 많아지면\n§f더이상 상점에 판매할 수 없게됩니다.");
        ccLang.get().addDefault("ITEM_SETTING.INF_STOCK", "무한 재고");
        ccLang.get().addDefault("ITEM_SETTING.STATIC_PRICE", "고정 가격");
        ccLang.get().addDefault("ITEM_SETTING.UNLIMITED", "무제한");
        ccLang.get().addDefault("ITEM_SETTING.MEDIAN_HELP", "§f중앙값이 작을수록 가격이 급격이 변화합니다.");
        ccLang.get().addDefault("ITEM_SETTING.TAX_IGNORED", "판매세 설정이 무시됩니다.");
        ccLang.get().addDefault("ITEM_SETTING.RECOMMEND", "§f추천 값 적용");
        ccLang.get().addDefault("ITEM_SETTING.DISCOUNT", "§f할인");
        ccLang.get().addDefault("ITEM_SETTING.DISCOUNT_LORE", "§f할인율: {num}%\n§e좌클릭: +10, 우클릭: -10");
        ccLang.get().addDefault("ITEM_SETTING.DISCOUNT_LORE_2", "§f할인율: {num}%\n§e좌클릭: -10, 우클릭: +10");
        ccLang.get().addDefault("ITEM_SETTING.DONE", "§f완료");
        ccLang.get().addDefault("ITEM_SETTING.DONE_LORE", "§f완료!");
        ccLang.get().addDefault("ITEM_SETTING.ROUND_DOWN", "§f내림");
        ccLang.get().addDefault("ITEM_SETTING.SET_TO_MEDIAN", "§f중앙값에 맞춤");
        ccLang.get().addDefault("ITEM_SETTING.SET_TO_STOCK", "§f재고에 맞춤");
        ccLang.get().addDefault("ITEM_SETTING.SET_TO_VALUE", "§f가격에 맞춤");
        ccLang.get().addDefault("ITEM_SETTING.CLOSE", "§f닫기");
        ccLang.get().addDefault("ITEM_SETTING.CLOSE_LORE", "§f§n클릭: 닫기");
        ccLang.get().addDefault("ITEM_SETTING.REMOVE", "§c제거");
        ccLang.get().addDefault("ITEM_SETTING.REMOVE_LORE", "§f이 아이템을 상점에서 제거합니다.");
        ccLang.get().addDefault("ITEM_SETTING.BUY", "§3§l구매: {num}");
        ccLang.get().addDefault("ITEM_SETTING.SELL", "§3§l판매: {num}");
        ccLang.get().addDefault("ITEM_SETTING.TRADE_LIMIT_SELL", "§f플레이어 당 판매량 제한");
        ccLang.get().addDefault("ITEM_SETTING.TRADE_LIMIT_SELL_LORE", "§f{num}\n§eLMB: -1, RMB: +1, Shift = x10");
        ccLang.get().addDefault("ITEM_SETTING.TRADE_LIMIT_BUY", "§f플레이어 당 구매량 제한");
        ccLang.get().addDefault("ITEM_SETTING.TRADE_LIMIT_BUY_LORE", "§f{num}\n§eLMB: -1, RMB: +1, Shift = x10");
        ccLang.get().addDefault("ITEM_SETTING.TRADE_LIMIT_INTERVAL", "§f거래량 제한 리셋 주기");
        ccLang.get().addDefault("ITEM_SETTING.TRADE_LIMIT_INTERVAL_LORE", "§f주기: {interval}\n§f다음 리셋: {time}\n§e좌클릭: -1시간, 우클릭: +1시간, 씨프트 = x12");
        ccLang.get().addDefault("ITEM_SETTING.TRADE_LIMIT_TIMER", "§f타이머 조정");
        ccLang.get().addDefault("ITEM_SETTING.TRADE_LIMIT_TIMER_LORE", "§f{num}\n§f다음 리셋: {time}\n§e좌클릭: -1시간, 우클릭: +1시간, 씨프트 = x12");

        ccLang.get().addDefault("TRADE_TITLE", "§3아이템 거래");
        ccLang.get().addDefault("TRADE.TOGGLE_SELLABLE", "§e클릭: 판매전용 토글");
        ccLang.get().addDefault("TRADE.TOGGLE_BUYABLE", "§e클릭: 구매전용 토글");
        ccLang.get().addDefault("TRADE.BUY_ONLY_LORE", "§f구매만 가능한 아이템");
        ccLang.get().addDefault("TRADE.SELL_ONLY_LORE", "§f판매만 가능한 아이템");
        ccLang.get().addDefault("TRADE.BALANCE", "§3내 잔액");
        ccLang.get().addDefault("TRADE.PRICE", "§f구매: {num}");
        ccLang.get().addDefault("TRADE.SELL_PRICE", "§f판매: {num}");
        ccLang.get().addDefault("TRADE.PRICE_DISCOUNTED", "§f구매: §8§m{num}§r §a{num2}");
        ccLang.get().addDefault("TRADE.SELL_PRICE_DISCOUNTED", "§f판매: §8§m{num}§r §c{num2}");
        ccLang.get().addDefault("TRADE.PRICE_EXP", "§f구매: {num}EXP");
        ccLang.get().addDefault("TRADE.SELL_PRICE_EXP", "§f판매: {num}EXP");
        ccLang.get().addDefault("TRADE.PRICE_DISCOUNTED_EXP", "§f구매: §8§m{num}EXP§r §a{num2}EXP");
        ccLang.get().addDefault("TRADE.SELL_PRICE_DISCOUNTED_EXP", "§f판매: §8§m{num}EXP§r §c{num2}EXP");
        ccLang.get().addDefault("TRADE.PRICE_PP", "§f구매: {num}PP");
        ccLang.get().addDefault("TRADE.SELL_PRICE_PP", "§f판매: {num}PP");
        ccLang.get().addDefault("TRADE.PRICE_DISCOUNTED_PP", "§f구매: §8§m{num}PP§r §a{num2}PP");
        ccLang.get().addDefault("TRADE.SELL_PRICE_DISCOUNTED_PP", "§f판매: §8§m{num}PP§r §c{num2}PP");
        ccLang.get().addDefault("TRADE.PRICE_JP", "§f구매: {num}JP");
        ccLang.get().addDefault("TRADE.SELL_PRICE_JP", "§f판매: {num}JP");
        ccLang.get().addDefault("TRADE.PRICE_DISCOUNTED_JP", "§f구매: §8§m{num}JP§r §a{num2}JP");
        ccLang.get().addDefault("TRADE.SELL_PRICE_DISCOUNTED_JP", "§f판매: §8§m{num}JP§r §c{num2}JP");
        ccLang.get().addDefault("TRADE.BUY", "§c구매");
        ccLang.get().addDefault("TRADE.SELL", "§2판매");
        ccLang.get().addDefault("TRADE.STOCK", "§8재고: ");
        ccLang.get().addDefault("TRADE.STACKS", "§8{num} 스택");
        ccLang.get().addDefault("TRADE.INF_STOCK", "§8무한 재고");
        ccLang.get().addDefault("TRADE.SHOP_BAL_INF", "§f상점 계좌 무제한");
        ccLang.get().addDefault("TRADE.SHOP_BAL", "§3상점 계좌 잔액 \n§f{num}");
        ccLang.get().addDefault("TRADE.CLICK_TO_BUY", "§c§n클릭: {amount}개 구매");
        ccLang.get().addDefault("TRADE.CLICK_TO_SELL", "§2§n클릭: {amount}개 판매");
        ccLang.get().addDefault("TRADE.PURCHASE_LIMIT_PER_PLAYER", "§a구매량 제한 : {num} 남음\n§a다음 리셋: {time}");
        ccLang.get().addDefault("TRADE.SALES_LIMIT_PER_PLAYER", "§a판매량 제한 : {num} 남음\n§a다음 리셋: {time}");

        ccLang.get().addDefault("PAGE_EDITOR_TITLE", "§3페이지 편집");
        ccLang.get().addDefault("PAGE_EDITOR.PREV", "§f<<");
        ccLang.get().addDefault("PAGE_EDITOR.NEXT", "§f>>");
        ccLang.get().addDefault("PAGE_EDITOR.PAGE_SWAP_SUCCESS", "§f페이지가 교체 되었습니다.");
        ccLang.get().addDefault("PAGE_EDITOR.PAGE_SWAP_FAIL", "§f페이지 교체 실패.");
        ccLang.get().addDefault("PAGE_EDITOR.PAGE_SWAP_SELECTED", "§f페이지 선택되었습니다. 서로 교체 할 다른 페이지를 우클릭 하세요.");
        ccLang.get().addDefault("PAGE_EDITOR.PAGE_LORE_PREMIUM", "§e좌클릭: 페이지 이동\n§e우클릭: 페이지 교체(스왑)\n§eShift+좌: 페이지 삽입\n§eShift+우: 페이지 삭제");
        ccLang.get().addDefault("PAGE_EDITOR.PRICE", "§f구매: {num}");
        ccLang.get().addDefault("PAGE_EDITOR.SELL_PRICE", "§f판매: {num}");
        ccLang.get().addDefault("PAGE_EDITOR.STOCK", "§8재고: {num}");
        ccLang.get().addDefault("PAGE_EDITOR.STACKS", "§8{num} 스택");
        ccLang.get().addDefault("PAGE_EDITOR.STATIC_PRICE", "§8[고정 가격]");
        ccLang.get().addDefault("PAGE_EDITOR.EMPTY", "§8(비어있음)");
        ccLang.get().addDefault("PAGE_EDITOR.EMPTY_SLOT_LORE", "§e좌,우클릭: 밀기\n§e+씨프트: 당기기");
        ccLang.get().addDefault("TRADE.QUANTITY_LORE", "§eShift+우: 수량 편집");
        ccLang.get().addDefault("TRADE.WAIT_FOR_INPUT", "거래 UI에 표시 될 수량을 입력하세요.\n예시: 1,2,4,8,16,32,64");

        ccLang.get().addDefault("LOG_VIEWER_TITLE", "§3로그 뷰어");
        ccLang.get().addDefault("LOG_VIEWER.DATE", "§f날짜: ");
        ccLang.get().addDefault("LOG_VIEWER.TIME", "§f시간: ");
        ccLang.get().addDefault("LOG_VIEWER.CURRENCY", "§f화폐 유형: ");
        ccLang.get().addDefault("LOG_VIEWER.PRICE", "§f가격: ");
        ccLang.get().addDefault("LOG_VIEWER.EXPAND", "§f펼치기");
        ccLang.get().addDefault("LOG_VIEWER.COLLAPSE", "§f접기");
        ccLang.get().addDefault("LOG_VIEWER.PAGE_TITLE", "§f{curPage}/{maxPage} 페이지");
        ccLang.get().addDefault("LOG_VIEWER.PAGE_LORE", "§e좌클릭: 이전 페이지\n§e우클릭: 다음 페이지");
        ccLang.get().addDefault("LOG_VIEWER.FILE_LORE", "§e좌클릭: 열기\n§eShift+우: §c삭제");


        ccLang.get().addDefault("LOG.LOG", "§f로그");
        ccLang.get().addDefault("LOG.CLEAR", "§f로그 삭제됨");
        ccLang.get().addDefault("LOG.SAVE", "§f로그 저장됨");
        ccLang.get().addDefault("LOG.DELETE", "§4로그 삭제");
        ccLang.get().addDefault("LOG.SELL", "§f{player}(이)가 {shop}에 {item} {amount}개를 판매함");
        ccLang.get().addDefault("LOG.BUY", "§f{player}(이)가 {shop}에서 {item} {amount}개를 구매함");

        ccLang.get().addDefault("STOCK_SIMULATOR_TITLE", "§3재고 시뮬레이터");
        ccLang.get().addDefault("STOCK_SIMULATOR.CHANGE_SAMPLE_LORE", "§e좌, 우클릭: 아이템 변경");
        ccLang.get().addDefault("STOCK_SIMULATOR.SIMULATOR_BUTTON_LORE", "§e우클릭: 시뮬레이터");
        ccLang.get().addDefault("STOCK_SIMULATOR.RUN_TITLE", "§f실행");
        ccLang.get().addDefault("STOCK_SIMULATOR.RUN_LORE", "§e좌클릭: 시뮬레이션 실행\n§e우클릭: 설정값을 상점에 적용합니다\n§f아이템은 영향받지 않습니다.");
        ccLang.get().addDefault("STOCK_SIMULATOR.REAL_TIME", "§a(실제 시간)");
        ccLang.get().addDefault("STOCK_SIMULATOR.AFTER_S", "§a{0}초 후");
        ccLang.get().addDefault("STOCK_SIMULATOR.AFTER_M", "§a{0}분 후");
        ccLang.get().addDefault("STOCK_SIMULATOR.AFTER_H", "§a{0}시간 후");
        ccLang.get().addDefault("STOCK_SIMULATOR.AFTER_D", "§a{0}일 후");
        ccLang.get().addDefault("STOCK_SIMULATOR.L_R_SHIFT", "§e좌: -1 우: +1 Shift: x5");
        ccLang.get().addDefault("STOCK_SIMULATOR.PRICE", "§f구매: {num}");
        ccLang.get().addDefault("STOCK_SIMULATOR.MEDIAN", "§f중앙값: {num}");
        ccLang.get().addDefault("STOCK_SIMULATOR.STOCK", "§f재고: {num}");

        ccLang.get().addDefault("PALETTE_TITLE", "§3판매할 아이템 선택");
        ccLang.get().addDefault("PALETTE_TITLE2", "§3아이템 선택");
        ccLang.get().addDefault("PALETTE.LORE_PREMIUM", "§e좌클릭: 추가\n§e씨프트 좌클릭: 설정 후 추가\n§e우클릭: 장식으로 추가\n§e씨프트 우클릭: '{item}' 를 검색");
        ccLang.get().addDefault("PALETTE.LORE2", "§e좌클릭: 선택\n§e씨프트 우클릭: '{item}' 를 검색");
        ccLang.get().addDefault("PALETTE.SEARCH", "§f찾기");
        ccLang.get().addDefault("PALETTE.ADD_ALL", "§f모두 추가");
        ccLang.get().addDefault("PALETTE.ADD_ALL_LORE", "§e좌클릭: 모두 추가\n§e씨프트 좌클릭: 모두 추가하고 권장 값 적용");
        ccLang.get().addDefault("PALETTE.PAGE_TITLE", "§f{curPage}/{maxPage} 페이지");
        ccLang.get().addDefault("PALETTE.PAGE_LORE", "§f§n좌클릭: 이전 페이지\n§f§n우클릭: 다음 페이지");
        ccLang.get().addDefault("PALETTE.FILTER_APPLIED", "§f필터 적용됨 : ");
        ccLang.get().addDefault("PALETTE.FILTER_LORE", "§f좌클릭: 검색\n§f우클릭: 필터 초기화\n\n§7\"BLUE_WOOL\"을 찾으려는 경우:\n§7 b w\n§7 wool\n§7 blue wool");

        ccLang.get().addDefault("QUICK_SELL_TITLE", "§3빠른 판매");
        ccLang.get().addDefault("QUICK_SELL.GUIDE_TITLE", "§3§l빠른 판매 도움말");
        ccLang.get().addDefault("QUICK_SELL.GUIDE_LORE", "§a판매할 아이템을 좌클릭 하세요.\n§a씨프트 좌클릭으로 같은 유형의 아이템을 모두 팝니다.\n§a우클릭으로 해당 아이템 상점으로 이동합니다.");

        ccLang.get().addDefault("ARROW.UP", "§a⬆");
        ccLang.get().addDefault("ARROW.DOWN", "§c⬇");
        ccLang.get().addDefault("ARROW.UP_2", "§c⬆");
        ccLang.get().addDefault("ARROW.DOWN_2", "§a⬇");

        ccLang.get().addDefault("TIME.OPEN", "Open");
        ccLang.get().addDefault("TIME.CLOSE", "Close");
        ccLang.get().addDefault("TIME.OPEN_LORE", "§f문 여는 시간 설정");
        ccLang.get().addDefault("TIME.CLOSE_LORE", "§f문 닫는 시간 설정");
        ccLang.get().addDefault("TIME.SHOPHOURS", "§f영업시간");
        ccLang.get().addDefault("TIME.OPEN24", "24시간 오픈");
        ccLang.get().addDefault("TIME.SHOP_IS_CLOSED", "§f상점이 문을 닫았습니다. 개점: {time}시. 현재시간: {curTime}시");
        ccLang.get().addDefault("TIME.SET_SHOPHOURS", "영업시간 설정");
        ccLang.get().addDefault("TIME.CUR", "§f현재 시간: {time}시");

        ccLang.get().addDefault("STOCK_STABILIZING.SS", "§f재고 안정화");
        ccLang.get().addDefault("STOCK_STABILIZING.L_R_SHIFT", "§e좌클릭: -0.1 우클릭: +0.1 Shift: x5");
        ccLang.get().addDefault("STOCK_STABILIZING.STRENGTH_LORE_A", "§f중앙값(median)의 n%");
        ccLang.get().addDefault("STOCK_STABILIZING.STRENGTH_LORE_B", "§f중앙값(median)과의 격차의 n%");

        ccLang.get().addDefault("FLUCTUATION.FLUCTUATION", "§f무작위 재고 변동");
        ccLang.get().addDefault("FLUCTUATION.INTERVAL", "§f변화 간격");
        ccLang.get().addDefault("FLUCTUATION.INTERVAL_LORE", "§f1h = 1000틱 = 현실시간 50초");
        ccLang.get().addDefault("FLUCTUATION.STRENGTH", "§f변화 강도");
        ccLang.get().addDefault("FLUCTUATION.STRENGTH_LORE", "§f중앙값(median)의 n%");

        ccLang.get().addDefault("TAX.SALES_TAX", "§f판매세");
        ccLang.get().addDefault("TAX.USE_GLOBAL", "전역설정 사용 ({tax}%)");
        ccLang.get().addDefault("TAX.USE_LOCAL", "별도 설정");

        ccLang.get().addDefault("MESSAGE.SEARCH_ITEM", "§f찾으려는 아이템의 이름을 입력하세요.");
        ccLang.get().addDefault("MESSAGE.SEARCH_CANCELED", "§f검색 취소됨.");
        ccLang.get().addDefault("MESSAGE.INPUT_CANCELED", "§f입력 취소됨.");
        ccLang.get().addDefault("MESSAGE.DELETE_CONFIRM", "§f정말로 페이지를 삭제할까요? 'delete' 를 입력하면 삭제합니다.");
        ccLang.get().addDefault("MESSAGE.CANT_DELETE_LAST_PAGE", "§f마지막 남은 페이지를 삭제할 수 없습니다.");
        ccLang.get().addDefault("MESSAGE.SHOP_BAL_LOW", "§f상점이 돈을 충분히 가지고 있지 않습니다.");
        ccLang.get().addDefault("MESSAGE.SHOP_CREATED", "§f상점 생성됨!");
        ccLang.get().addDefault("MESSAGE.SHOP_DELETED", "§f상점 제거됨!");
        ccLang.get().addDefault("MESSAGE.OUT_OF_STOCK", "§f재고 없음!");
        ccLang.get().addDefault("MESSAGE.BUY_SUCCESS", "§f{item} {amount}개를 {price}에 구매함. 잔액: {bal}");
        ccLang.get().addDefault("MESSAGE.SELL_SUCCESS", "§f{item} {amount}개를 {price}에 판매함. 잔액: {bal}");
        ccLang.get().addDefault("MESSAGE.BUY_SUCCESS_EXP", "§f{item} {amount}개를 {price}경험치 포인트에 구매함. 잔액: {bal}");
        ccLang.get().addDefault("MESSAGE.SELL_SUCCESS_EXP", "§f{item} {amount}개를 {price}경험치 포인트에 판매함. 잔액: {bal}");
        ccLang.get().addDefault("MESSAGE.BUY_SUCCESS_JP", "§f{item} {amount}개를 {price}포인트에 구매함. 남은포인트: {bal}");
        ccLang.get().addDefault("MESSAGE.SELL_SUCCESS_JP", "§f{item} {amount}개를 {price}포인트에 판매함. 남은포인트: {bal}");
        ccLang.get().addDefault("MESSAGE.BUY_SUCCESS_PP", "§f{item} {amount}개를 {price}포인트에 구매함. 남은포인트: {bal}");
        ccLang.get().addDefault("MESSAGE.SELL_SUCCESS_PP", "§f{item} {amount}개를 {price}포인트에 판매함. 남은포인트: {bal}");
        ccLang.get().addDefault("MESSAGE.QSELL_NA", "§f해당 아이템을 취급하는 상점이 없습니다.");
        ccLang.get().addDefault("MESSAGE.DELIVERY_CHARGE", "§f배달비: {fee}");
        ccLang.get().addDefault("MESSAGE.DELIVERY_CHARGE_NA", "§f다른 월드로 배달할 수 없습니다.");
        ccLang.get().addDefault("MESSAGE.NOT_ENOUGH_MONEY", "§f돈이 부족합니다. 잔액: {bal}");
        ccLang.get().addDefault("MESSAGE.NOT_ENOUGH_POINT", "§f포인트가 부족합니다. 잔액: {bal}");
        ccLang.get().addDefault("MESSAGE.NOT_ENOUGH_PLAYER_POINT", "§f플레이어 포인트가 부족합니다. 잔액: {bal}");
        ccLang.get().addDefault("MESSAGE.NOT_ENOUGH_EXP_POINT", "§f경험치 포인트가 부족합니다. 잔액: {bal}");
        ccLang.get().addDefault("MESSAGE.NO_ITEM_TO_SELL", "§f판매 할 아이템이 없습니다.");
        ccLang.get().addDefault("MESSAGE.NO_ITEM_TO_SELL_2", "§f판매 가능 한 아이템이 없습니다.");
        ccLang.get().addDefault("MESSAGE.INVENTORY_FULL", "§4인벤토리에 빈 공간이 없습니다!");
        ccLang.get().addDefault("MESSAGE.IRREVERSIBLE", "§f이 행동은 되돌릴 수 없습니다!");
        ccLang.get().addDefault("MESSAGE.ITEM_ADDED", "아이템 추가됨!");
        ccLang.get().addDefault("MESSAGE.ITEM_UPDATED", "아이템 수정됨!");
        ccLang.get().addDefault("MESSAGE.ITEM_DELETED", "아이템 제거됨!");
        ccLang.get().addDefault("MESSAGE.CHANGES_APPLIED", "변경사항 적용됨. 새로운 값: ");
        ccLang.get().addDefault("MESSAGE.CHANGES_APPLIED_2", "변경사항 적용됨");
        ccLang.get().addDefault("MESSAGE.RECOMMEND_APPLIED", "추천 값 적용됨. {playerNum}명 기준입니다. config파일에서 이 값을 바꿀 수 있습니다.");
        ccLang.get().addDefault("MESSAGE.TRANSFER_SUCCESS", "송금 완료");
        ccLang.get().addDefault("MESSAGE.PURCHASE_REJECTED", "상점에 이 아이템이 너무 많습니다. 지금은 팔 수 없습니다.");
        ccLang.get().addDefault("MESSAGE.CLICK_YOUR_ITEM_START_PAGE", "인벤토리의 아이템을 클릭하면 가장 좋은 조건의 상점으로 이동합니다.\n좌클릭: 구매   우클릭: 판매");
        ccLang.get().addDefault("MESSAGE.MOVE_TO_BEST_SHOP_BUY", "{item}을 가장 저렴하게 살 수 있는 상점으로 이동했습니다.");
        ccLang.get().addDefault("MESSAGE.MOVE_TO_BEST_SHOP_SELL", "{item}을 가장 비싸게 팔 수 있는 상점으로 이동했습니다.");
        ccLang.get().addDefault("MESSAGE.SHOP_IS_CLOSED_BY_ADMIN", "이 상점은 서버 관리자에 의해 닫혔습니다.");
        ccLang.get().addDefault("MESSAGE.SHOP_DISABLED", "이 상점은 비황성화된 상태입니다. 어드민이 아닌 유저는 접근할 수 없습니다. 상점 설정에서 활성화 할 수 있습니다.");
        ccLang.get().addDefault("MESSAGE.ROTATION_SHARED_DATA_MISSING", "§e[ ! ]§f'{0}/SharedData.yml' 에서 {1} 개의 아이템에 대한 정보를 찾을 수 없었습니다. 이 아이템들은 장식으로 추가되었습니다.");
        ccLang.get().addDefault("MESSAGE.ENTER_COMMAND", "명령어를 '/' 제외하고 입력하세요.");
        ccLang.get().addDefault("MESSAGE.ENTER_COMMAND_2", "'인덱스/명령어' 형태로 입력하세요.");
        ccLang.get().addDefault("MESSAGE.SELL_COMMAND_CUR", "현재 적용중인 판매 명령어:");
        ccLang.get().addDefault("MESSAGE.BUY_COMMAND_CUR", "현재 적용중인 구매 명령어:");
        ccLang.get().addDefault("MESSAGE.Q_SEARCH_FAIL_CURRENCY", "해당 아이템은 여러 종류의 재화로 거래 중 입니다.");

        ccLang.get().addDefault("HELP.TITLE", "§f도움말: {command} --------------------");
        ccLang.get().addDefault("HELP.SHOP", "상점을 엽니다.");
        ccLang.get().addDefault("HELP.CMD", "명령어 도움말 표시 토글.");
        ccLang.get().addDefault("HELP.CREATE_SHOP", "상점을 새로 만듭니다.");
        ccLang.get().addDefault("HELP.CREATE_SHOP_2", "퍼미션(나중에 바꿀 수 있습니다.)\n   true: dshop.user.shop.상점이름\n   false: 아무나 접근가능(기본값)\n   임의 입력: 해당 퍼미션 필요");
        ccLang.get().addDefault("HELP.DELETE_SHOP", "기존의 상점을 제거합니다.");
        ccLang.get().addDefault("HELP.SHOP_ADD_HAND", "손에 들고 있는 아이템을 상점에 추가합니다.");
        ccLang.get().addDefault("HELP.SHOP_ADD_ITEM", "상점에 아이템을 추가합니다.");
        ccLang.get().addDefault("HELP.SHOP_EDIT", "상점에 있는 아이템을 수정합니다.");
        ccLang.get().addDefault("HELP.PRICE", "§7가격은 다음과 같이 계산됩니다: median*value/stock");
        ccLang.get().addDefault("HELP.INF_STATIC", "§7median<0 == 고정가격     stock<0 == 무한재고");
        ccLang.get().addDefault("HELP.EDIT_ALL", "상점의 모든 아이템을 한번에 수정합니다.");
        ccLang.get().addDefault("HELP.RELOAD", "플러그인을 재시작 합니다.");
        ccLang.get().addDefault("HELP.RELOADED", "플러그인 리로드됨!");
        ccLang.get().addDefault("HELP.USAGE", "사용법");
        ccLang.get().addDefault("HELP.ITEM_ALREADY_EXIST", "§7§o{item}(은)는 이미 판매중임.\n   {info}\n   명령어를 입력하면 값이 수정됩니다.");
        ccLang.get().addDefault("HELP.ITEM_INFO", "§7§o{item}의 현재 설정:\n   {info}");
        ccLang.get().addDefault("HELP.REMOVE_ITEM", "§f인자를 0으로 입력하면 이 아이템을 상점에서 §4제거§f합니다.");
        ccLang.get().addDefault("HELP.QSELL", "§f빠르게 아이템을 판매합니다.");
        ccLang.get().addDefault("HELP.DELETE_OLD_USER", "장기간 접속하지 않은 유저의 데이터를 삭제합니다.");
        ccLang.get().addDefault("HELP.ACCOUNT", "상점의 계좌 잔액을 설정합니다. -1 = 무제한");
        ccLang.get().addDefault("HELP.SET_TO_REC_ALL", "§e상점의 모든 아이템 설정값을 권장값으로 §c초기화§e합니다.");
        ccLang.get().addDefault("HELP.SHOP_ENABLE", "상점을 활성화 또는 비활성화 합니다.");

        ccLang.get().addDefault("ERR.NO_USER_ID", "§6플레이어 uuid를 찾을 수 없습니다. 상점 이용 불가능.");
        ccLang.get().addDefault("ERR.ITEM_NOT_EXIST", "상점에 해당 아이템이 존재하지 않습니다.");
        ccLang.get().addDefault("ERR.ITEM_FORBIDDEN", "사용할 수 없는 아이템 입니다.");
        ccLang.get().addDefault("ERR.NO_PERMISSION", "§e권한이 없습니다.");
        ccLang.get().addDefault("ERR.WRONG_USAGE", "잘못된 명령어 사용법. 도움말을 확인하세요.");
        ccLang.get().addDefault("ERR.NO_EMPTY_SLOT", "상점에 빈 공간이 없습니다.");
        ccLang.get().addDefault("ERR.WRONG_DATATYPE", "인자의 유형이 잘못 입력되었습니다.");
        ccLang.get().addDefault("ERR.VALUE_ZERO", "인자값이 0보다 커야 합니다.");
        ccLang.get().addDefault("ERR.WRONG_ITEM_NAME", "유효하지 않은 아이템 이름입니다.");
        ccLang.get().addDefault("ERR.HAND_EMPTY", "아이템을 손에 들고 있어야 합니다.");
        ccLang.get().addDefault("ERR.HAND_EMPTY2", "§c§o아이템을 손에 들고 있어야 합니다!");
        ccLang.get().addDefault("ERR.SHOP_NOT_FOUND", "§f해당 상점을 찾을 수 없습니다.");
        ccLang.get().addDefault("ERR.SHOP_EXIST", "해당 이름을 가진 상점이 이미 존재합니다.");
        ccLang.get().addDefault("ERR.SHOP_NOT_EXIST", "그런 이름을 가진 상점이 없습니다.");
        ccLang.get().addDefault("ERR.SIGN_SHOP_REMOTE_ACCESS", "해당 상점은 표지판을 통해서만 접근할 수 있습니다.");
        ccLang.get().addDefault("ERR.LOCAL_SHOP_REMOTE_ACCESS", "해당 상점은 직접 방문해야만 사용할 수 있습니다.");
        ccLang.get().addDefault("ERR.MAX_LOWER_THAN_MIN", "최대 가격은 최소 가격보다 커야합니다.");
        ccLang.get().addDefault("ERR.DEFAULT_VALUE_OUT_OF_RANGE", "기본 가격은 최소 가격과 최대 가격 사이의 값이어야 합니다.");
        ccLang.get().addDefault("ERR.NO_RECOMMEND_DATA", "Worth.yml 파일에 이 아이템의 정보가 없습니다.");
        ccLang.get().addDefault("ERR.JOBS_REBORN_NOT_FOUND", "Jobs reborn 플러그인을 찾을 수 없습니다.");
        ccLang.get().addDefault("ERR.PLAYER_POINTS_NOT_FOUND", "Player points 플러그인을 찾을 수 없습니다.");
        ccLang.get().addDefault("ERR.SHOP_HAS_INF_BAL", "{shop} 상점은 무한계좌 상점입니다.");
        ccLang.get().addDefault("ERR.SHOP_DIFF_CURRENCY", "두 상점이 서로 다른 통화를 사용합니다.");
        ccLang.get().addDefault("ERR.PLAYER_NOT_EXIST", "해당 플레이어를 찾을 수 없습니다.");
        ccLang.get().addDefault("ERR.SHOP_LINK_FAIL", "상점 둘 중 하나는 실제 계좌이어야 합니다.");
        ccLang.get().addDefault("ERR.SHOP_LINK_TARGET_ERR", "목표 상점은 실제 계좌를 가지고 있어야 합니다.");
        ccLang.get().addDefault("ERR.NESTED_STRUCTURE", "계층 구조를 이루는것은 금지되어 있습니다. (ex. aa-bb, bb-cc)");
        ccLang.get().addDefault("ERR.CREATIVE", "§eCreative mode 에서 이 명령어를 사용할 수 없습니다. 권한이 없습니다.");
        ccLang.get().addDefault("ERR.FILE_CREATE_FAIL", "§e파일 생성에 실패했습니다.");
        ccLang.get().addDefault("ERR.INVALID_TRANSACTION", "이 거래는 더이상 유효하지 않습니다. 문제가 반복되면 서버 관리자에게 문의하세요.");
        ccLang.get().addDefault("ERR.SIGN_WALL", "상점 표지판이 벽에 설치되야 합니다.");

        ccLang.get().addDefault("ERR.SHOP_NULL", "§e상점 이름이 지정되지 않았습니다.");
        ccLang.get().addDefault("ERR.ITEMINFO_HAND_EMPTY", "아이템을 들고 있어야 합니다.");
        ccLang.get().addDefault("HELP.ITEMINFO_USAGE", "§f아이템 정보를 보려면 손에 아이템을 들고 있어야 합니다.");
        ccLang.get().addDefault("HELP.ITEMINFO_REALNAME", "§7실제 이름: §3{item_realname}");
        ccLang.get().addDefault("HELP.ITEMINFO_SIGN_NAME", "§7표지판용 이름: §3{item_signname}");
        ccLang.get().addDefault("ERR.SIGN_ITEM_INVALID", "아이템 이름이 잘못되었습니다. /ds iteminfo 를 사용하여 아이템 이름을 확인해 보세요.");
        ccLang.get().addDefault("ERR.SIGN_ITEM_NOT_FOR_SALE", "상점에 없는 아이템 입니다.");
        ccLang.get().addDefault("MESSAGE.SIGN_SHOP_CREATED", "§a표지판 상점 생성!");

        ccLang.get().addDefault("ON", "켜짐");
        ccLang.get().addDefault("OFF", "꺼짐");
        ccLang.get().addDefault("SET", "설정");
        ccLang.get().addDefault("UNSET", "설정해제");
        ccLang.get().addDefault("NULL", "없음");
        ccLang.get().addDefault("NULL(OPEN)", "없음 (모두에게 열려있음)");
        ccLang.get().addDefault("CUR_STATE", "현재상태");
        ccLang.get().addDefault("CLICK", "클릭");
        ccLang.get().addDefault("LMB", "좌클릭");
        ccLang.get().addDefault("RMB", "우클릭");
        ccLang.get().addDefault("CLOSE", "§f닫기");
        ccLang.get().addDefault("CLOSE_LORE", "§f§n클릭: 닫기");

        ccLang.get().addDefault("EXP_POINTS", "Exp");
        ccLang.get().addDefault("JOB_POINTS", "Job Points");
        ccLang.get().addDefault("PLAYER_POINTS", "Player Points");

        ccLang.get().options().copyDefaults(true);
        ccLang.save();
    }

    private static void zh_TW()
    {
        ccLang.setup("Lang_V3_zh-TW", null);

        ccLang.get().addDefault("START_PAGE.EDITOR_TITLE", "§3編輯圖示");
        ccLang.get().addDefault("START_PAGE.EDIT_NAME", "§f重新命名");
        ccLang.get().addDefault("START_PAGE.EDIT_LORE", "§f修改描述");
        ccLang.get().addDefault("START_PAGE.EDIT_ICON", "§f更換圖示");
        ccLang.get().addDefault("START_PAGE.EDIT_ACTION", "§f更改指令");
        ccLang.get().addDefault("START_PAGE.SHOP_SHORTCUT", "§f建立商店按鈕");
        ccLang.get().addDefault("START_PAGE.CREATE_DECO", "§f建立裝飾按鈕");
        ccLang.get().addDefault("START_PAGE.ENTER_SHOP_NAME", "請輸入商店名稱");
        ccLang.get().addDefault("START_PAGE.DEFAULT_SHOP_LORE", "§f§n點擊：前往商店");
        ccLang.get().addDefault("START_PAGE.ITEM_MOVE_LORE", "§e右鍵：移動");
        ccLang.get().addDefault("START_PAGE.ITEM_COPY_LORE", "§e右鍵：複製");
        ccLang.get().addDefault("START_PAGE.ITEM_REMOVE_LORE", "§eShift 左鍵：移除");
        ccLang.get().addDefault("START_PAGE.ITEM_EDIT_LORE", "§eShift 右鍵：編輯");
        ccLang.get().addDefault("START_PAGE.REMOVE", "§f移除");
        ccLang.get().addDefault("START_PAGE.REMOVE_LORE", "§f從開始頁面移除此按鈕。");
        ccLang.get().addDefault("START_PAGE.ENTER_NAME", "請輸入此按鈕的新名稱。");
        ccLang.get().addDefault("START_PAGE.ENTER_LORE", "請輸入此按鈕的新描述。");
        ccLang.get().addDefault("START_PAGE.ENTER_ICON", "請輸入要用作按鈕圖示的物品名稱。（英文，不區分大小寫）");
        ccLang.get().addDefault("START_PAGE.ENTER_ACTION", "請輸入指令（不含 /）。當按下按鈕時會執行此指令。");
        ccLang.get().addDefault("START_PAGE.ENTER_COLOR", "請輸入裝飾按鈕的顏色。（英文）");
        ccLang.get().addDefault("START_PAGE.SHOP_LIST_TITLE", "§3商店清單");
        ccLang.get().addDefault("START_PAGE.SHOP_LIST.PAGE_TITLE", "§f{curPage}/{maxPage} 頁");
        ccLang.get().addDefault("START_PAGE.SHOP_LIST.PAGE_LORE", "§e左鍵：上一頁\n§e右鍵：下一頁");

        ccLang.get().addDefault("COLOR_PICKER_TITLE", "§3顏色選擇器");

        ccLang.get().addDefault("SHOP.TRADE_LORE", "§f§n點擊：交易");
        ccLang.get().addDefault("SHOP.BUY_PRICE", "§f購買：{num}");
        ccLang.get().addDefault("SHOP.SELL_PRICE", "§f出售：{num}");
        ccLang.get().addDefault("SHOP.BUY_PRICE_DISCOUNTED", "§f購買：§8§m{num}§r §a{num2}");
        ccLang.get().addDefault("SHOP.SELL_PRICE_DISCOUNTED", "§f出售：§8§m{num}§r §c{num2}");
        ccLang.get().addDefault("SHOP.BUY_PRICE_EXP", "§f購買：{num} 經驗值");
        ccLang.get().addDefault("SHOP.SELL_PRICE_EXP", "§f出售：{num} 經驗值");
        ccLang.get().addDefault("SHOP.BUY_PRICE_DISCOUNTED_EXP", "§f購買：§8§m{num} 經驗值§r §a{num2} 經驗值");
        ccLang.get().addDefault("SHOP.SELL_PRICE_DISCOUNTED_EXP", "§f出售：§8§m{num} 經驗值§r §c{num2} 經驗值");
        ccLang.get().addDefault("SHOP.BUY_PRICE_PP", "§f購買：{num} 積分");
        ccLang.get().addDefault("SHOP.SELL_PRICE_PP", "§f出售：{num} 積分");
        ccLang.get().addDefault("SHOP.BUY_PRICE_DISCOUNTED_PP", "§f購買：§8§m{num} 積分§r §a{num2} 積分");
        ccLang.get().addDefault("SHOP.SELL_PRICE_DISCOUNTED_PP", "§f出售：§8§m{num} 積分§r §c{num2} 積分");
        ccLang.get().addDefault("SHOP.BUY_PRICE_JP", "§f購買：{num} 職業點數");
        ccLang.get().addDefault("SHOP.SELL_PRICE_JP", "§f出售：{num} 職業點數");
        ccLang.get().addDefault("SHOP.BUY_PRICE_DISCOUNTED_JP", "§f購買：§8§m{num} 職業點數§r §a{num2} 職業點數");
        ccLang.get().addDefault("SHOP.SELL_PRICE_DISCOUNTED_JP", "§f出售：§8§m{num} 職業點數§r §c{num2} 職業點數");
        ccLang.get().addDefault("SHOP.STOCK", "§8庫存：{num}");
        ccLang.get().addDefault("SHOP.STOCK_2", "§8庫存：{stock}/{max_stock}");
        ccLang.get().addDefault("SHOP.INF_STOCK", "§8無限");
        ccLang.get().addDefault("SHOP.STATIC_PRICE", "§8【固定價格】");
        ccLang.get().addDefault("SHOP.STACKS", "§8{num} 組");
        ccLang.get().addDefault("SHOP.ITEM_MOVE_LORE", "§e右鍵：移動");
        ccLang.get().addDefault("SHOP.ITEM_COPY_LORE", "§e右鍵：複製");
        ccLang.get().addDefault("SHOP.ITEM_EDIT_LORE", "§eShift右鍵：編輯");
        ccLang.get().addDefault("SHOP.DECO_DELETE_LORE", "§eShift右鍵：移除");
        ccLang.get().addDefault("SHOP.PAGE_TITLE", "§f{curPage}/{maxPage} 頁");
        ccLang.get().addDefault("SHOP.PAGE_LORE_V2", "§f§n左鍵：上一頁\n§f§n右鍵：下一頁\n§7點擊你的物品會\n§7跳轉到該物品所在頁面。");
        ccLang.get().addDefault("SHOP.GO_TO_PAGE_EDITOR", "§eShift 右鍵：頁面編輯器");
        ccLang.get().addDefault("SHOP.ITEM_MOVE_SELECTED", "已選擇物品。右鍵點擊空欄位以移動。");
        ccLang.get().addDefault("SHOP.PERMISSION", "§f權限：");
        ccLang.get().addDefault("SHOP.PERMISSION_ITEM", "§7 - {permission}");
        ccLang.get().addDefault("SHOP.FLAGS", "§e標記：");
        ccLang.get().addDefault("SHOP.FLAGS_ITEM", "§e - {flag}");
        ccLang.get().addDefault("SHOP.SHOP_BAL_INF", "§f無限");
        ccLang.get().addDefault("SHOP.SHOP_BAL", "§f商店帳戶餘額");
        ccLang.get().addDefault("SHOP.SHOP_LOCATION", "§f商店位置：x {x}, y {y}, z {z}");
        ccLang.get().addDefault("SHOP.SHOP_LOCATION_B", "§f商店位置：");
        ccLang.get().addDefault("SHOP.SHOP_INFO_DASH", "§7 - ");
        ccLang.get().addDefault("SHOP.DISABLED", "§c已停用§8｜§f");
        ccLang.get().addDefault("SHOP.INCOMPLETE_DATA", "資料不完整");
        ccLang.get().addDefault("SHOP.INCOMPLETE_DATA_Lore", "此物品對非 OP 玩家不可見\n索引：");
        ccLang.get().addDefault("SHOP.TRADE_LIMIT_BUY", "§a購買上限：剩餘 {num}");
        ccLang.get().addDefault("SHOP.TRADE_LIMIT_SELL", "§a販售上限：剩餘 {num}");
        ccLang.get().addDefault("SHOP.TRADE_LIMIT_TIMER", "§a下次重置：{time}");
        ccLang.get().addDefault("SHOP.CLICK_TO_ADD", "§e點擊：新增");

        ccLang.get().addDefault("SHOP_SETTING_TITLE", "§3商店設定");
        ccLang.get().addDefault("SHOP_SETTING.LOG_TOGGLE_LORE", "§e右鍵：查看紀錄");
        ccLang.get().addDefault("SHOP_SETTING.LOG_PRINT_CONSOLE", "§f將紀錄輸出至主控台");
        ccLang.get().addDefault("SHOP_SETTING.LOG_PRINT_ADMIN", "§f將紀錄發送給管理員");
        ccLang.get().addDefault("SHOP_SETTING.MAX_PAGE", "§f最大頁數");
        ccLang.get().addDefault("SHOP_SETTING.MAX_PAGE_LORE", "§f設定商店最大頁數。");
        ccLang.get().addDefault("SHOP_SETTING.BACKGROUND", "§f背景顏色");
        ccLang.get().addDefault("SHOP_SETTING.BACKGROUND_LORE", "§e點擊：變更顏色");
        ccLang.get().addDefault("SHOP_SETTING.L_R_SHIFT", "§e左鍵：-1 右鍵：+1 Shift：x5");
        ccLang.get().addDefault("SHOP_SETTING.FLAG", "§f標記");
        ccLang.get().addDefault("SHOP_SETTING.SHOP_SETTINGS_LORE", "§e右鍵：商店設定");
        ccLang.get().addDefault("SHOP_SETTING.SIGN_SHOP_LORE", "§f僅可透過告示牌進入。");
        ccLang.get().addDefault("SHOP_SETTING.LOCAL_SHOP_LORE", "§f必須親訪實體商店位置。");
        ccLang.get().addDefault("SHOP_SETTING.DELIVERY_CHARGE_LORE", "§f您可以付費配送，\n§f無需親訪商店交易。");
        ccLang.get().addDefault("SHOP_SETTING.SELECTED", "§2已選擇");
        ccLang.get().addDefault("SHOP_SETTING.CURRENCY", "§f貨幣類型：");
        ccLang.get().addDefault("SHOP_SETTING.VAULT_LORE", "§f預設貨幣。");
        ccLang.get().addDefault("SHOP_SETTING.EXP_LORE", "§f以玩家經驗值交易。");
        ccLang.get().addDefault("SHOP_SETTING.JOB_POINT_LORE", "§f使用職業點數交易。\n§f需安裝 Jobs Reborn 插件");
        ccLang.get().addDefault("SHOP_SETTING.PLAYER_POINT_LORE", "§f使用玩家積分交易。\n§f需安裝 PlayerPoints 插件");
        ccLang.get().addDefault("SHOP_SETTING.SHOW_VALUE_CHANGE_LORE", "§f顯示價格變動幅度。");
        ccLang.get().addDefault("SHOP_SETTING.HIDE_STOCK", "§f隱藏庫存。");
        ccLang.get().addDefault("SHOP_SETTING.HIDE_PRICING_TYPE", "§f隱藏價格類型。");
        ccLang.get().addDefault("SHOP_SETTING.HIDE_SHOP_BALANCE", "§f隱藏商店帳戶餘額。");
        ccLang.get().addDefault("SHOP_SETTING.SHOW_MAX_STOCK", "§f顯示最大庫存。");
        ccLang.get().addDefault("SHOP_SETTING.HIDDEN_IN_COMMAND", "§f此商店不會\n§f出現在指令補全中。");
        ccLang.get().addDefault("SHOP_SETTING.INTEGER_ONLY", "§f購買價格將向上取整。\n§f販售價格將向下取整。");
        ccLang.get().addDefault("SHOP_SETTING.PERMISSION", "§f權限");
        ccLang.get().addDefault("SHOP_SETTING.STATE", "§f狀態");
        ccLang.get().addDefault("SHOP_SETTING.STATE_ENABLE", "§a啟用");
        ccLang.get().addDefault("SHOP_SETTING.STATE_DISABLE", "§c停用");
        ccLang.get().addDefault("SHOP_SETTING.ROTATION_EDITOR", "§f輪換：");
        ccLang.get().addDefault("SHOP_SETTING.ROTATION_EDITOR_LORE", "§e點擊：編輯輪換");
        ccLang.get().addDefault("SHOP_SETTING.COMMAND_TOGGLE", "§f伺服器指令");
        ccLang.get().addDefault("SHOP_SETTING.COMMAND_TOGGLE_LORE", "§f在商店交易時執行伺服器指令。");
        ccLang.get().addDefault("SHOP_SETTING.COMMAND_SELL", "§f販售時執行");
        ccLang.get().addDefault("SHOP_SETTING.COMMAND_BUY", "§f購買時執行");
        ccLang.get().addDefault("SHOP_SETTING.COMMAND_LORE1", "§e左鍵：設定");
        ccLang.get().addDefault("SHOP_SETTING.COMMAND_LORE3", "§eShift 右鍵：刪除最後一項");
        ccLang.get().addDefault("SHOP_SETTING.TRADE_UI", "§f編輯交易介面");
        ccLang.get().addDefault("SHOP_SETTING.TRADE_UI_LORE_2", "§e左鍵：編輯\n§e右鍵：重設");

        ccLang.get().addDefault("ROTATION_EDITOR_TITLE", "§3輪換編輯器");
        ccLang.get().addDefault("ROTATION_EDITOR.ENABLED", "§a已啟用");
        ccLang.get().addDefault("ROTATION_EDITOR.DISABLED", "§c已停用");
        ccLang.get().addDefault("ROTATION_EDITOR.CLICK_TO_ENABLE", "§e左鍵：啟用");
        ccLang.get().addDefault("ROTATION_EDITOR.CLICK_TO_DISABLE", "§e左鍵：停用");
        ccLang.get().addDefault("ROTATION_EDITOR.CURRENT_TIME", "§f當前時間：");
        ccLang.get().addDefault("ROTATION_EDITOR.CURRENTLY_IN_USE", "§a【使用中】");
        ccLang.get().addDefault("ROTATION_EDITOR.NEXT_ROTATION", "§f下次輪換：");
        ccLang.get().addDefault("ROTATION_EDITOR.PERIOD", "§f週期");
        ccLang.get().addDefault("ROTATION_EDITOR.PERIOD_LORE_V2", "§e左鍵：-1小時，右鍵：+1小時，Shift：x10");
        ccLang.get().addDefault("ROTATION_EDITOR.TIMER", "§f計時器調整");
        ccLang.get().addDefault("ROTATION_EDITOR.TIMER_LORE_V2", "§e左鍵：-10分鐘，右鍵：+10分鐘，Shift：x6");
        ccLang.get().addDefault("ROTATION_EDITOR.HOUR", "§f{0} 小時");
        ccLang.get().addDefault("ROTATION_EDITOR.UNSAVED_CHANGES", "§c§o尚未儲存的變更");
        ccLang.get().addDefault("ROTATION_EDITOR.APPLY_CHANGES", "§f套用變更");
        ccLang.get().addDefault("ROTATION_EDITOR.APPLY_CHANGES_LORE", "§e左鍵：儲存變更\n§e右鍵：重設變更");
        ccLang.get().addDefault("ROTATION_EDITOR.APPLY_ROTATION", "§e左鍵：立即套用");
        ccLang.get().addDefault("ROTATION_EDITOR.OPEN", "§e左鍵：開啟");
        ccLang.get().addDefault("ROTATION_EDITOR.CREATE", "§e左鍵：建立空白");
        ccLang.get().addDefault("ROTATION_EDITOR.COPY_AS_NEW", "§e右鍵：複製為新項目");
        ccLang.get().addDefault("ROTATION_EDITOR.DELETE", "§eShift 右鍵：§c刪除");
        ccLang.get().addDefault("ROTATION_EDITOR.REAPPLY", "§eShift 左鍵：重新套用");
        ccLang.get().addDefault("ROTATION_EDITOR.MOVE", "§e右鍵：移動");

        ccLang.get().addDefault("ITEM_SETTING_TITLE", "§3物品設定");
        ccLang.get().addDefault("ITEM_SETTING.VALUE_BUY", "§f購買價值：");
        ccLang.get().addDefault("ITEM_SETTING.VALUE_SELL", "§f販售價值：");
        ccLang.get().addDefault("ITEM_SETTING.PRICE", "§f購買價格：");
        ccLang.get().addDefault("ITEM_SETTING.SELL_PRICE", "§f販售價格：");
        ccLang.get().addDefault("ITEM_SETTING.PRICE_MIN", "§f最低價格：");
        ccLang.get().addDefault("ITEM_SETTING.PRICE_MAX", "§f最高價格：");
        ccLang.get().addDefault("ITEM_SETTING.MEDIAN", "§f中位數：");
        ccLang.get().addDefault("ITEM_SETTING.STOCK", "§f庫存：");
        ccLang.get().addDefault("ITEM_SETTING.MAX_STOCK", "§f最大庫存：");
        ccLang.get().addDefault("ITEM_SETTING.MAX_STOCK_LORE", "§f若庫存超過此上限，\n§f商店將拒絕收購。");
        ccLang.get().addDefault("ITEM_SETTING.INF_STOCK", "無限庫存");
        ccLang.get().addDefault("ITEM_SETTING.STATIC_PRICE", "固定價格");
        ccLang.get().addDefault("ITEM_SETTING.UNLIMITED", "無限制");
        ccLang.get().addDefault("ITEM_SETTING.MEDIAN_HELP", "§f中位數越小，\n§f價格變化越劇烈。");
        ccLang.get().addDefault("ITEM_SETTING.TAX_IGNORED", "已忽略銷售稅設定。");
        ccLang.get().addDefault("ITEM_SETTING.RECOMMEND", "§f套用建議數值");
        ccLang.get().addDefault("ITEM_SETTING.DISCOUNT", "§f折扣");
        ccLang.get().addDefault("ITEM_SETTING.DISCOUNT_LORE", "§f折扣率：{num}%\n§e左鍵：+10，右鍵：-10");
        ccLang.get().addDefault("ITEM_SETTING.DISCOUNT_LORE_2", "§f折扣率：{num}%\n§e左鍵：-10，右鍵：+10");
        ccLang.get().addDefault("ITEM_SETTING.DONE", "§f完成");
        ccLang.get().addDefault("ITEM_SETTING.DONE_LORE", "§f設定完成！");
        ccLang.get().addDefault("ITEM_SETTING.ROUND_DOWN", "§f向下取整");
        ccLang.get().addDefault("ITEM_SETTING.SET_TO_MEDIAN", "§f設為中位數");
        ccLang.get().addDefault("ITEM_SETTING.SET_TO_STOCK", "§f設為庫存數");
        ccLang.get().addDefault("ITEM_SETTING.SET_TO_VALUE", "§f設為數值");
        ccLang.get().addDefault("ITEM_SETTING.CLOSE", "§f關閉");
        ccLang.get().addDefault("ITEM_SETTING.CLOSE_LORE", "§f§n點擊：關閉");
        ccLang.get().addDefault("ITEM_SETTING.REMOVE", "§c移除");
        ccLang.get().addDefault("ITEM_SETTING.REMOVE_LORE", "§f從商店中移除此物品。");
        ccLang.get().addDefault("ITEM_SETTING.BUY", "§3§l購買：{num}");
        ccLang.get().addDefault("ITEM_SETTING.SELL", "§3§l販售：{num}");
        ccLang.get().addDefault("ITEM_SETTING.TRADE_LIMIT_SELL", "§f每位玩家販售上限");
        ccLang.get().addDefault("ITEM_SETTING.TRADE_LIMIT_SELL_LORE", "§f{num}\n§e左鍵：-1，右鍵：+1，Shift = x10");
        ccLang.get().addDefault("ITEM_SETTING.TRADE_LIMIT_BUY", "§f每位玩家購買上限");
        ccLang.get().addDefault("ITEM_SETTING.TRADE_LIMIT_BUY_LORE", "§f{num}\n§e左鍵：-1，右鍵：+1，Shift = x10");
        ccLang.get().addDefault("ITEM_SETTING.TRADE_LIMIT_INTERVAL", "§f交易量限制重設週期");
        ccLang.get().addDefault("ITEM_SETTING.TRADE_LIMIT_INTERVAL_LORE", "§f間隔時間：{interval}\n§f下次重置：{time}\n§e左鍵：-1 小時，右鍵：+1 小時，Shift = x12");
        ccLang.get().addDefault("ITEM_SETTING.TRADE_LIMIT_TIMER", "§f調整計時器");
        ccLang.get().addDefault("ITEM_SETTING.TRADE_LIMIT_TIMER_LORE", "§f{num}\n§f下次重置：{time}\n§e左鍵：-1 小時，右鍵：+1 小時，Shift = x12");

        ccLang.get().addDefault("TRADE_TITLE", "§3交易");
        ccLang.get().addDefault("TRADE.TOGGLE_SELLABLE", "§e點擊：僅限販售切換");
        ccLang.get().addDefault("TRADE.TOGGLE_BUYABLE", "§e點擊：僅限購買切換");
        ccLang.get().addDefault("TRADE.BUY_ONLY_LORE", "§f此物品無法販售。");
        ccLang.get().addDefault("TRADE.SELL_ONLY_LORE", "§f此物品無法購買。");
        ccLang.get().addDefault("TRADE.BALANCE", "§3我的餘額");
        ccLang.get().addDefault("TRADE.PRICE", "§f購買：{num}");
        ccLang.get().addDefault("TRADE.SELL_PRICE", "§f販售：{num}");
        ccLang.get().addDefault("TRADE.PRICE_DISCOUNTED", "§f購買：§8§m{num}§r §a{num2}");
        ccLang.get().addDefault("TRADE.SELL_PRICE_DISCOUNTED", "§f販售：§8§m{num}§r §c{num2}");
        ccLang.get().addDefault("TRADE.PRICE_EXP", "§f購買：{num} 經驗值");
        ccLang.get().addDefault("TRADE.SELL_PRICE_EXP", "§f販售：{num} 經驗值");
        ccLang.get().addDefault("TRADE.PRICE_DISCOUNTED_EXP", "§f購買：§8§m{num} 經驗值§r §a{num2} 經驗值");
        ccLang.get().addDefault("TRADE.SELL_PRICE_DISCOUNTED_EXP", "§f販售：§8§m{num} 經驗值§r §c{num2} 經驗值");
        ccLang.get().addDefault("TRADE.PRICE_PP", "§f購買：{num} 積分");
        ccLang.get().addDefault("TRADE.SELL_PRICE_PP", "§f販售：{num} 積分");
        ccLang.get().addDefault("TRADE.PRICE_DISCOUNTED_PP", "§f購買：§8§m{num} 積分§r §a{num2} 積分");
        ccLang.get().addDefault("TRADE.SELL_PRICE_DISCOUNTED_PP", "§f販售：§8§m{num} 積分§r §c{num2} 積分");
        ccLang.get().addDefault("TRADE.PRICE_JP", "§f購買：{num} 職業點數");
        ccLang.get().addDefault("TRADE.SELL_PRICE_JP", "§f販售：{num} 職業點數");
        ccLang.get().addDefault("TRADE.PRICE_DISCOUNTED_JP", "§f購買：§8§m{num} 職業點數§r §a{num2} 職業點數");
        ccLang.get().addDefault("TRADE.SELL_PRICE_DISCOUNTED_JP", "§f販售：§8§m{num} 職業點數§r §c{num2} 職業點數");
        ccLang.get().addDefault("TRADE.BUY", "§c購買");
        ccLang.get().addDefault("TRADE.SELL", "§2販售");
        ccLang.get().addDefault("TRADE.STOCK", "§8庫存：");
        ccLang.get().addDefault("TRADE.STACKS", "§8{num} 組");
        ccLang.get().addDefault("TRADE.INF_STOCK", "§8無限");
        ccLang.get().addDefault("TRADE.SHOP_BAL_INF", "§f無限");
        ccLang.get().addDefault("TRADE.SHOP_BAL", "§3商店帳戶餘額 \n§f{num}");
        ccLang.get().addDefault("TRADE.CLICK_TO_BUY", "§c§n點擊：購買 {amount}");
        ccLang.get().addDefault("TRADE.CLICK_TO_SELL", "§2§n點擊：販售 {amount}");
        ccLang.get().addDefault("TRADE.PURCHASE_LIMIT_PER_PLAYER", "§a購買上限：剩餘 {num}\n§a下次重置：{time}");
        ccLang.get().addDefault("TRADE.SALES_LIMIT_PER_PLAYER", "§a販售上限：剩餘 {num}\n§a下次重置：{time}");
        ccLang.get().addDefault("TRADE.QUANTITY_LORE", "§eShift 右鍵：編輯數量");
        ccLang.get().addDefault("TRADE.WAIT_FOR_INPUT", "輸入要在交易介面中顯示的數量。\n範例：1,2,4,8,16,32,64");

        ccLang.get().addDefault("PAGE_EDITOR_TITLE", "§3頁面編輯器");
        ccLang.get().addDefault("PAGE_EDITOR.PREV", "§f<<");
        ccLang.get().addDefault("PAGE_EDITOR.NEXT", "§f>>");
        ccLang.get().addDefault("PAGE_EDITOR.PAGE_SWAP_SUCCESS", "§f頁面已成功替換。");
        ccLang.get().addDefault("PAGE_EDITOR.PAGE_SWAP_FAIL", "§f頁面替換失敗。");
        ccLang.get().addDefault("PAGE_EDITOR.PAGE_SWAP_SELECTED", "§f頁面已選取。請右鍵點擊要替換的其他頁面。");
        ccLang.get().addDefault("PAGE_EDITOR.PAGE_LORE_PREMIUM", "§e左鍵：開啟頁面\n§e右鍵：替換\n§eShift 左鍵：插入\n§eShift 右鍵：刪除");
        ccLang.get().addDefault("PAGE_EDITOR.PRICE", "§f購買：{num}");
        ccLang.get().addDefault("PAGE_EDITOR.SELL_PRICE", "§f販售：{num}");
        ccLang.get().addDefault("PAGE_EDITOR.STOCK", "§8庫存：{num}");
        ccLang.get().addDefault("PAGE_EDITOR.STACKS", "§8{num} 組");
        ccLang.get().addDefault("PAGE_EDITOR.STATIC_PRICE", "§8【固定價格】");
        ccLang.get().addDefault("PAGE_EDITOR.EMPTY", "§8（空）");
        ccLang.get().addDefault("PAGE_EDITOR.EMPTY_SLOT_LORE", "§e左鍵／右鍵：推送\n§e+Shift：拉取");

        ccLang.get().addDefault("LOG_VIEWER_TITLE", "§3紀錄檢視器");
        ccLang.get().addDefault("LOG_VIEWER.DATE", "§f日期：");
        ccLang.get().addDefault("LOG_VIEWER.TIME", "§f時間：");
        ccLang.get().addDefault("LOG_VIEWER.CURRENCY", "§f貨幣：");
        ccLang.get().addDefault("LOG_VIEWER.PRICE", "§f價格：");
        ccLang.get().addDefault("LOG_VIEWER.EXPAND", "§f展開");
        ccLang.get().addDefault("LOG_VIEWER.COLLAPSE", "§f收合");
        ccLang.get().addDefault("LOG_VIEWER.PAGE_TITLE", "§f{curPage}/{maxPage} 頁");
        ccLang.get().addDefault("LOG_VIEWER.PAGE_LORE", "§e左鍵：上一頁\n§e右鍵：下一頁");
        ccLang.get().addDefault("LOG_VIEWER.FILE_LORE", "§e左鍵：開啟\n§eShift 右鍵：§c刪除");

        ccLang.get().addDefault("LOG.LOG", "§f紀錄");
        ccLang.get().addDefault("LOG.CLEAR", "§f紀錄已刪除");
        ccLang.get().addDefault("LOG.SAVE", "§f紀錄已儲存");
        ccLang.get().addDefault("LOG.DELETE", "§4刪除紀錄");
        ccLang.get().addDefault("LOG.SELL", "§f{player} 將 {amount} 個 {item} 販售給 {shop}");
        ccLang.get().addDefault("LOG.BUY", "§f{player} 從 {shop} 購買了 {amount} 個 {item}");

        ccLang.get().addDefault("STOCK_SIMULATOR_TITLE", "§3庫存模擬器");
        ccLang.get().addDefault("STOCK_SIMULATOR.CHANGE_SAMPLE_LORE", "§e左鍵／右鍵：變更物品");
        ccLang.get().addDefault("STOCK_SIMULATOR.SIMULATOR_BUTTON_LORE", "§e右鍵：模擬器");
        ccLang.get().addDefault("STOCK_SIMULATOR.RUN_TITLE", "§f執行");
        ccLang.get().addDefault("STOCK_SIMULATOR.RUN_LORE", "§e左鍵：執行模擬\n§e右鍵：將設定套用至商店\n§f物品本身不受影響。");
        ccLang.get().addDefault("STOCK_SIMULATOR.REAL_TIME", "§a（即時）");
        ccLang.get().addDefault("STOCK_SIMULATOR.AFTER_S", "§a{0} 秒後");
        ccLang.get().addDefault("STOCK_SIMULATOR.AFTER_M", "§a{0} 分鐘後");
        ccLang.get().addDefault("STOCK_SIMULATOR.AFTER_H", "§a{0} 小時後");
        ccLang.get().addDefault("STOCK_SIMULATOR.AFTER_D", "§a{0} 天後");
        ccLang.get().addDefault("STOCK_SIMULATOR.L_R_SHIFT", "§e左鍵：-1 右鍵：+1 Shift：x5");
        ccLang.get().addDefault("STOCK_SIMULATOR.PRICE", "§f購買價格：{num}");
        ccLang.get().addDefault("STOCK_SIMULATOR.MEDIAN", "§f中位數：{num}");
        ccLang.get().addDefault("STOCK_SIMULATOR.STOCK", "§f庫存：{num}");

        ccLang.get().addDefault("PALETTE_TITLE", "§3選擇要販售的物品");
        ccLang.get().addDefault("PALETTE_TITLE2", "§3選擇物品");
        ccLang.get().addDefault("PALETTE.LORE_PREMIUM", "§e左鍵：新增\n§eShift 左鍵：設定後新增\n§e右鍵：新增為裝飾物\n§eShift 右鍵：搜尋「{item}」");
        ccLang.get().addDefault("PALETTE.LORE2", "§e左鍵：選擇\n§eShift 右鍵：搜尋「{item}」");
        ccLang.get().addDefault("PALETTE.SEARCH", "§f搜尋");
        ccLang.get().addDefault("PALETTE.ADD_ALL", "§f全部新增");
        ccLang.get().addDefault("PALETTE.ADD_ALL_LORE", "§e左鍵：新增全部\n§eShift 左鍵：新增全部並套用建議數值");
        ccLang.get().addDefault("PALETTE.PAGE_TITLE", "§f{curPage}/{maxPage} 頁");
        ccLang.get().addDefault("PALETTE.PAGE_LORE", "§f§n左鍵：上一頁\n§f§n右鍵：下一頁");
        ccLang.get().addDefault("PALETTE.FILTER_APPLIED", "§f篩選器已套用：");
        ccLang.get().addDefault("PALETTE.FILTER_LORE", "§e左鍵：搜尋\n§e右鍵：清除篩選\n\n§7搜尋「BLUE_WOOL」範例：\n§7 b w\n§7 wool\n§7 blue wool");

        ccLang.get().addDefault("QUICK_SELL_TITLE", "§3快速販售");
        ccLang.get().addDefault("QUICK_SELL.GUIDE_TITLE", "§3§l快速販售指南");
        ccLang.get().addDefault("QUICK_SELL.GUIDE_LORE", "§a左鍵點擊要販售的物品。\n§aShift 左鍵可販售所有相同類型的物品。\n§a右鍵可前往該物品的商店。");

        ccLang.get().addDefault("ARROW.UP", "§a⬆");
        ccLang.get().addDefault("ARROW.DOWN", "§c⬇");
        ccLang.get().addDefault("ARROW.UP_2", "§c⬆");
        ccLang.get().addDefault("ARROW.DOWN_2", "§a⬇");

        ccLang.get().addDefault("TIME.OPEN", "開門");
        ccLang.get().addDefault("TIME.CLOSE", "關門");
        ccLang.get().addDefault("TIME.OPEN_LORE", "§f設定開店時間");
        ccLang.get().addDefault("TIME.CLOSE_LORE", "§f設定關店時間");
        ccLang.get().addDefault("TIME.SHOPHOURS", "§f營業時間");
        ccLang.get().addDefault("TIME.OPEN24", "24 小時營業");
        ccLang.get().addDefault("TIME.SHOP_IS_CLOSED", "§f商店目前關閉，將於 {time} 點開啟。現在時間 {curTime} 點。");
        ccLang.get().addDefault("TIME.SET_SHOPHOURS", "設定營業時間");
        ccLang.get().addDefault("TIME.CUR", "§f目前時間：{time} 點");

        ccLang.get().addDefault("STOCK_STABILIZING.SS", "§f庫存穩定化");
        ccLang.get().addDefault("STOCK_STABILIZING.L_R_SHIFT", "§e左鍵：-0.1 右鍵：+0.1 Shift：x5");
        ccLang.get().addDefault("STOCK_STABILIZING.STRENGTH_LORE_A", "§f中位數的 n%");
        ccLang.get().addDefault("STOCK_STABILIZING.STRENGTH_LORE_B", "§f與中位數差距的 n%");

        ccLang.get().addDefault("FLUCTUATION.FLUCTUATION", "§f庫存波動");
        ccLang.get().addDefault("FLUCTUATION.INTERVAL", "§f間隔時間");
        ccLang.get().addDefault("FLUCTUATION.INTERVAL_LORE", "§f1 小時 = 1000 ticks = 實際時間 50 秒");
        ccLang.get().addDefault("FLUCTUATION.STRENGTH", "§f波動強度");
        ccLang.get().addDefault("FLUCTUATION.STRENGTH_LORE", "§f中位數的 n%");

        ccLang.get().addDefault("TAX.SALES_TAX", "§f銷售稅");
        ccLang.get().addDefault("TAX.USE_GLOBAL", "使用全域設定（{tax}%）");
        ccLang.get().addDefault("TAX.USE_LOCAL", "單獨設定");

        ccLang.get().addDefault("MESSAGE.SEARCH_ITEM", "§f請輸入要搜尋的物品名稱。");
        ccLang.get().addDefault("MESSAGE.SEARCH_CANCELED", "§f搜尋已取消。");
        ccLang.get().addDefault("MESSAGE.INPUT_CANCELED", "§f輸入已取消。");
        ccLang.get().addDefault("MESSAGE.DELETE_CONFIRM", "§f確定要刪除此頁面嗎？請輸入 'delete' 以確認。");
        ccLang.get().addDefault("MESSAGE.CANT_DELETE_LAST_PAGE", "§f無法刪除僅剩的最後一頁。");
        ccLang.get().addDefault("MESSAGE.SHOP_BAL_LOW", "§f商店資金不足。");
        ccLang.get().addDefault("MESSAGE.SHOP_CREATED", "§f商店已建立！");
        ccLang.get().addDefault("MESSAGE.SHOP_DELETED", "§f商店已刪除！");
        ccLang.get().addDefault("MESSAGE.OUT_OF_STOCK", "§f缺貨！");
        ccLang.get().addDefault("MESSAGE.BUY_SUCCESS", "§f已購買 {item} x{amount}，花費 {price}。餘額：{bal}");
        ccLang.get().addDefault("MESSAGE.SELL_SUCCESS", "§f已販售 {item} x{amount}，收入 {price}。餘額：{bal}");
        ccLang.get().addDefault("MESSAGE.BUY_SUCCESS_EXP", "§f已購買 {item} x{amount}，花費 {price} 經驗值。餘額：{bal}");
        ccLang.get().addDefault("MESSAGE.SELL_SUCCESS_EXP", "§f已販售 {item} x{amount}，收入 {price} 經驗值。餘額：{bal}");
        ccLang.get().addDefault("MESSAGE.BUY_SUCCESS_JP", "§f已購買 {item} x{amount}，花費 {price} 點數。剩餘：{bal}");
        ccLang.get().addDefault("MESSAGE.SELL_SUCCESS_JP", "§f已販售 {item} x{amount}，收入 {price} 點數。剩餘：{bal}");
        ccLang.get().addDefault("MESSAGE.BUY_SUCCESS_PP", "§f已購買 {item} x{amount}，花費 {price} 積分。剩餘：{bal}");
        ccLang.get().addDefault("MESSAGE.SELL_SUCCESS_PP", "§f已販售 {item} x{amount}，收入 {price} 積分。剩餘：{bal}");
        ccLang.get().addDefault("MESSAGE.QSELL_NA", "§f沒有任何商店販售此物品。");
        ccLang.get().addDefault("MESSAGE.DELIVERY_CHARGE", "§f運費：{fee}");
        ccLang.get().addDefault("MESSAGE.DELIVERY_CHARGE_NA", "§f無法配送至其他世界。");
        ccLang.get().addDefault("MESSAGE.NOT_ENOUGH_MONEY", "§f金錢不足。餘額：{bal}");
        ccLang.get().addDefault("MESSAGE.NOT_ENOUGH_POINT", "§f點數不足。餘額：{bal}");
        ccLang.get().addDefault("MESSAGE.NOT_ENOUGH_PLAYER_POINT", "§f玩家積分不足。餘額：{bal}");
        ccLang.get().addDefault("MESSAGE.NOT_ENOUGH_EXP_POINT", "§f經驗值不足。餘額：{bal}");
        ccLang.get().addDefault("MESSAGE.NO_ITEM_TO_SELL", "§f目前沒有可販售的物品。");
        ccLang.get().addDefault("MESSAGE.NO_ITEM_TO_SELL_2", "§f無可販售的物品。");
        ccLang.get().addDefault("MESSAGE.INVENTORY_FULL", "§4你的物品欄已滿！");
        ccLang.get().addDefault("MESSAGE.IRREVERSIBLE", "§f此操作無法復原！");
        ccLang.get().addDefault("MESSAGE.ITEM_ADDED", "物品已新增！");
        ccLang.get().addDefault("MESSAGE.ITEM_UPDATED", "物品已更新！");
        ccLang.get().addDefault("MESSAGE.ITEM_DELETED", "物品已刪除！");
        ccLang.get().addDefault("MESSAGE.CHANGES_APPLIED", "變更已套用。新數值：");
        ccLang.get().addDefault("MESSAGE.CHANGES_APPLIED_2", "變更已套用");
        ccLang.get().addDefault("MESSAGE.RECOMMEND_APPLIED", "已套用建議值。此值基於 {playerNum} 名玩家，可於設定檔中更改。");
        ccLang.get().addDefault("MESSAGE.TRANSFER_SUCCESS", "匯款成功");
        ccLang.get().addDefault("MESSAGE.PURCHASE_REJECTED", "商店已擁有太多此物品，暫時無法販售。");
        ccLang.get().addDefault("MESSAGE.CLICK_YOUR_ITEM_START_PAGE", "點擊物品查看哪間商店提供最優惠的交易。\n左鍵：購買　右鍵：販售");
        ccLang.get().addDefault("MESSAGE.MOVE_TO_BEST_SHOP_BUY", "已跳轉至 {item} 最低購買價的商店。");
        ccLang.get().addDefault("MESSAGE.MOVE_TO_BEST_SHOP_SELL", "已跳轉至 {item} 最高販售價的商店。");
        ccLang.get().addDefault("MESSAGE.SHOP_IS_CLOSED_BY_ADMIN", "此商店已由伺服器管理員關閉。");
        ccLang.get().addDefault("MESSAGE.SHOP_DISABLED", "此商店目前已停用，非管理員無法使用。可於商店設定中重新啟用。");
        ccLang.get().addDefault("MESSAGE.ROTATION_SHARED_DATA_MISSING", "§e[ ! ]§f'{0}/SharedData.yml' 中找不到所需資料，共 {1} 個物品。這些物品已作為裝飾項新增。");
        ccLang.get().addDefault("MESSAGE.ENTER_COMMAND", "請輸入不含 '/' 的指令。");
        ccLang.get().addDefault("MESSAGE.ENTER_COMMAND_2", "請輸入格式為「索引/指令」。");
        ccLang.get().addDefault("MESSAGE.SELL_COMMAND_CUR", "目前作用中的販售指令：");
        ccLang.get().addDefault("MESSAGE.BUY_COMMAND_CUR", "目前作用中的購買指令：");
        ccLang.get().addDefault("MESSAGE.Q_SEARCH_FAIL_CURRENCY", "此物品正在使用多種貨幣進行交易。");

        ccLang.get().addDefault("HELP.TITLE", "§f說明：{command} --------------------");
        ccLang.get().addDefault("HELP.SHOP", "開啟商店");
        ccLang.get().addDefault("HELP.CMD", "切換是否顯示指令說明");
        ccLang.get().addDefault("HELP.CREATE_SHOP", "建立新商店。");
        ccLang.get().addDefault("HELP.CREATE_SHOP_2", "權限設定（可日後更改）：\n   true：需 dshop.user.shop.shopName 權限\n   false：所有人可存取（預設）\n   自訂值：需指定權限");
        ccLang.get().addDefault("HELP.DELETE_SHOP", "刪除現有商店。");
        ccLang.get().addDefault("HELP.SHOP_ADD_HAND", "將手上物品新增至商店。");
        ccLang.get().addDefault("HELP.SHOP_ADD_ITEM", "新增物品至商店。");
        ccLang.get().addDefault("HELP.SHOP_EDIT", "編輯商店內的物品。");
        ccLang.get().addDefault("HELP.PRICE", "§7價格計算公式：中位數 * 價值 ÷ 庫存");
        ccLang.get().addDefault("HELP.INF_STATIC", "§7中位數 < 0 為固定價格，庫存 < 0 為無限庫存");
        ccLang.get().addDefault("HELP.EDIT_ALL", "一次修改商店內所有物品。");
        ccLang.get().addDefault("HELP.RELOAD", "重新載入插件。");
        ccLang.get().addDefault("HELP.RELOADED", "插件已重新載入");
        ccLang.get().addDefault("HELP.USAGE", "使用方式");
        ccLang.get().addDefault("HELP.ITEM_ALREADY_EXIST", "§7§o{item} 已在販售中。\n   {info}\n   輸入指令可修改其數值。");
        ccLang.get().addDefault("HELP.ITEM_INFO", "§7§o{item} 目前設定：\n   {info}");
        ccLang.get().addDefault("HELP.REMOVE_ITEM", "§f輸入參數為 0 即§4刪除§f該物品。");
        ccLang.get().addDefault("HELP.QSELL", "§f快速販售物品。");
        ccLang.get().addDefault("HELP.DELETE_OLD_USER", "刪除長期未登入的使用者資料");
        ccLang.get().addDefault("HELP.ACCOUNT", "設定商店帳戶餘額，-1 為無上限");
        ccLang.get().addDefault("HELP.SET_TO_REC_ALL", "§c重設§e 商店內所有物品為建議值");
        ccLang.get().addDefault("HELP.SHOP_ENABLE", "啟用或停用商店");
        ccLang.get().addDefault("HELP.ITEMINFO_USAGE", "§f拿著物品可查看詳細資訊。");
        ccLang.get().addDefault("HELP.ITEMINFO_REALNAME", "§7實際名稱：§3{item_realname}");
        ccLang.get().addDefault("HELP.ITEMINFO_SIGN_NAME", "§7告示牌名稱：§3{item_signname}");

        ccLang.get().addDefault("ERR.NO_USER_ID", "§6找不到玩家 UUID。無法使用商店。");
        ccLang.get().addDefault("ERR.ITEM_NOT_EXIST", "商店中沒有此物品。");
        ccLang.get().addDefault("ERR.ITEM_FORBIDDEN", "此為禁用物品。");
        ccLang.get().addDefault("ERR.NO_PERMISSION", "§e你沒有權限。");
        ccLang.get().addDefault("ERR.WRONG_USAGE", "指令使用方式錯誤。");
        ccLang.get().addDefault("ERR.NO_EMPTY_SLOT", "商店中沒有空位。");
        ccLang.get().addDefault("ERR.WRONG_DATATYPE", "無效的參數類型");
        ccLang.get().addDefault("ERR.VALUE_ZERO", "參數值必須大於 0。");
        ccLang.get().addDefault("ERR.WRONG_ITEM_NAME", "無效的物品名稱。");
        ccLang.get().addDefault("ERR.HAND_EMPTY", "請手持物品。");
        ccLang.get().addDefault("ERR.HAND_EMPTY2", "§c§o你必須手持物品！");
        ccLang.get().addDefault("ERR.SHOP_NOT_FOUND", "§f找不到該商店。");
        ccLang.get().addDefault("ERR.SHOP_EXIST", "此商店名稱已存在。");
        ccLang.get().addDefault("ERR.SHOP_NOT_EXIST", "找不到指定商店。");
        ccLang.get().addDefault("ERR.SIGN_SHOP_REMOTE_ACCESS", "此商店僅能透過告示牌使用。");
        ccLang.get().addDefault("ERR.LOCAL_SHOP_REMOTE_ACCESS", "此商店需親訪現場。");
        ccLang.get().addDefault("ERR.MAX_LOWER_THAN_MIN", "最大價格必須高於最小價格。");
        ccLang.get().addDefault("ERR.DEFAULT_VALUE_OUT_OF_RANGE", "預設價格必須介於最小與最大價格之間。");
        ccLang.get().addDefault("ERR.NO_RECOMMEND_DATA", "Worth.yml 中沒有此物品的資訊。");
        ccLang.get().addDefault("ERR.JOBS_REBORN_NOT_FOUND", "找不到 Jobs Reborn 插件。");
        ccLang.get().addDefault("ERR.PLAYER_POINTS_NOT_FOUND", "找不到 PlayerPoints 插件。");
        ccLang.get().addDefault("ERR.SHOP_HAS_INF_BAL", "{shop} 是無限餘額商店。");
        ccLang.get().addDefault("ERR.SHOP_DIFF_CURRENCY", "兩間商店使用不同貨幣。");
        ccLang.get().addDefault("ERR.PLAYER_NOT_EXIST", "找不到該玩家。");
        ccLang.get().addDefault("ERR.SHOP_LINK_FAIL", "兩間商店必須有一間為實體帳戶。");
        ccLang.get().addDefault("ERR.SHOP_LINK_TARGET_ERR", "目標商店必須是實體帳戶。");
        ccLang.get().addDefault("ERR.NESTED_STRUCTURE", "禁止巢狀結構。（例如 aa-bb、bb-cc）");
        ccLang.get().addDefault("ERR.CREATIVE", "§e創造模式中不可使用此指令。你沒有權限。");
        ccLang.get().addDefault("ERR.FILE_CREATE_FAIL", "§e檔案建立失敗");
        ccLang.get().addDefault("ERR.INVALID_TRANSACTION", "此交易已無效。若問題持續，請聯繫伺服器管理員。");
        ccLang.get().addDefault("ERR.SIGN_WALL", "告示牌必須放置於牆上。");
        ccLang.get().addDefault("ERR.SHOP_NULL", "§e商店名稱不能為空。");
        ccLang.get().addDefault("ERR.ITEMINFO_HAND_EMPTY", "你必須手持物品。");
        ccLang.get().addDefault("ERR.SIGN_ITEM_INVALID", "無效的物品。請使用 /ds iteminfo 查詢名稱。");
        ccLang.get().addDefault("ERR.SIGN_ITEM_NOT_FOR_SALE", "請先將物品新增至商店，才能放置告示牌。");
        ccLang.get().addDefault("MESSAGE.SIGN_SHOP_CREATED", "§a已建立告示牌商店！");

        ccLang.get().addDefault("ON", "開啟");
        ccLang.get().addDefault("OFF", "關閉");
        ccLang.get().addDefault("SET", "設定");
        ccLang.get().addDefault("UNSET", "取消設定");
        ccLang.get().addDefault("NULL", "空值");
        ccLang.get().addDefault("NULL(OPEN)", "無（對所有人開放）");
        ccLang.get().addDefault("CUR_STATE", "當前狀態");
        ccLang.get().addDefault("CLICK", "點擊");
        ccLang.get().addDefault("LMB", "左鍵");
        ccLang.get().addDefault("RMB", "右鍵");
        ccLang.get().addDefault("CLOSE", "§f關閉");
        ccLang.get().addDefault("CLOSE_LORE", "§f§n點擊：關閉");

        ccLang.get().addDefault("EXP_POINTS", "經驗值 (EXP)");
        ccLang.get().addDefault("JOB_POINTS", "職業點數 (Job Points)");
        ccLang.get().addDefault("PLAYER_POINTS", "玩家點數 (Player Points)");

        ccLang.get().options().copyDefaults(true);
        ccLang.save();
    }

    private static void en_US()
    {
        ccLang.setup("Lang_V3_en-US", null);

        ccLang.get().addDefault("START_PAGE.EDITOR_TITLE", "§3Edit icon");
        ccLang.get().addDefault("START_PAGE.EDIT_NAME", "§fRename");
        ccLang.get().addDefault("START_PAGE.EDIT_LORE", "§fChange lore");
        ccLang.get().addDefault("START_PAGE.EDIT_ICON", "§fChange icon");
        ccLang.get().addDefault("START_PAGE.EDIT_ACTION", "§fChange command");
        ccLang.get().addDefault("START_PAGE.SHOP_SHORTCUT", "§fCreate shop button");
        ccLang.get().addDefault("START_PAGE.CREATE_DECO", "§fCreate decorative button");
        ccLang.get().addDefault("START_PAGE.ENTER_SHOP_NAME", "Please enter shop name");
        ccLang.get().addDefault("START_PAGE.DEFAULT_SHOP_LORE", "§f§nClick: go to shop");
        ccLang.get().addDefault("START_PAGE.ITEM_MOVE_LORE", "§eRMB: Move");
        ccLang.get().addDefault("START_PAGE.ITEM_COPY_LORE", "§eRMB: Copy");
        ccLang.get().addDefault("START_PAGE.ITEM_REMOVE_LORE", "§eShift LMB: Remove");
        ccLang.get().addDefault("START_PAGE.ITEM_EDIT_LORE", "§eShift RMB: Edit");
        ccLang.get().addDefault("START_PAGE.REMOVE", "§fRemove");
        ccLang.get().addDefault("START_PAGE.REMOVE_LORE", "§fRemove this button from the start page.");
        ccLang.get().addDefault("START_PAGE.ENTER_NAME", "Enter a new name for the button.");
        ccLang.get().addDefault("START_PAGE.ENTER_LORE", "Enter a new description for the button.");
        ccLang.get().addDefault("START_PAGE.ENTER_ICON", "Enter the name of the item to be used as the icon for the button. (English, case insensitive)");
        ccLang.get().addDefault("START_PAGE.ENTER_ACTION", "Enter the command without '/'. This command is executed when the button is pressed.");
        ccLang.get().addDefault("START_PAGE.ENTER_COLOR", "Enter a color for the decorative button. (English)");
        ccLang.get().addDefault("START_PAGE.SHOP_LIST_TITLE", "§3Shop List");
        ccLang.get().addDefault("START_PAGE.SHOP_LIST.PAGE_TITLE", "§f{curPage}/{maxPage} Page");
        ccLang.get().addDefault("START_PAGE.SHOP_LIST.PAGE_LORE", "§eLMB: Previous page\n§eRMB: Next page");

        ccLang.get().addDefault("COLOR_PICKER_TITLE", "§3Color Picker");

        ccLang.get().addDefault("SHOP.TRADE_LORE", "§f§nClick: Trade");
        ccLang.get().addDefault("SHOP.BUY_PRICE", "§fBuy: {num}");
        ccLang.get().addDefault("SHOP.SELL_PRICE", "§fSell: {num}");
        ccLang.get().addDefault("SHOP.BUY_PRICE_DISCOUNTED", "§fBuy: §8§m{num}§r §a{num2}");
        ccLang.get().addDefault("SHOP.SELL_PRICE_DISCOUNTED", "§fSell: §8§m{num}§r §c{num2}");
        ccLang.get().addDefault("SHOP.BUY_PRICE_EXP", "§fBuy: {num}Exp");
        ccLang.get().addDefault("SHOP.SELL_PRICE_EXP", "§fSell: {num}Exp");
        ccLang.get().addDefault("SHOP.BUY_PRICE_DISCOUNTED_EXP", "§fBuy: §8§m{num}Exp§r §a{num2}Exp");
        ccLang.get().addDefault("SHOP.SELL_PRICE_DISCOUNTED_EXP", "§fSell: §8§m{num}Exp§r §c{num2}Exp");
        ccLang.get().addDefault("SHOP.BUY_PRICE_PP", "§fBuy: {num}PP");
        ccLang.get().addDefault("SHOP.SELL_PRICE_PP", "§fSell: {num}PP");
        ccLang.get().addDefault("SHOP.BUY_PRICE_DISCOUNTED_PP", "§fBuy: §8§m{num}PP§r §a{num2}PP");
        ccLang.get().addDefault("SHOP.SELL_PRICE_DISCOUNTED_PP", "§fSell: §8§m{num}PP§r §c{num2}PP");
        ccLang.get().addDefault("SHOP.BUY_PRICE_JP", "§fBuy: {num}JP");
        ccLang.get().addDefault("SHOP.SELL_PRICE_JP", "§fSell: {num}JP");
        ccLang.get().addDefault("SHOP.BUY_PRICE_DISCOUNTED_JP", "§fBuy: §8§m{num}JP§r §a{num2}JP");
        ccLang.get().addDefault("SHOP.SELL_PRICE_DISCOUNTED_JP", "§fSell: §8§m{num}JP§r §c{num2}JP");
        ccLang.get().addDefault("SHOP.STOCK", "§8Stock: {num}");
        ccLang.get().addDefault("SHOP.STOCK_2", "§8Stock: {stock}/{max_stock}");
        ccLang.get().addDefault("SHOP.INF_STOCK", "§8Infinite");
        ccLang.get().addDefault("SHOP.STATIC_PRICE", "§8[Fixed price]");
        ccLang.get().addDefault("SHOP.STACKS", "§8{num} Stacks");
        ccLang.get().addDefault("SHOP.ITEM_MOVE_LORE", "§eRMB: Move");
        ccLang.get().addDefault("SHOP.ITEM_COPY_LORE", "§eRMB: Copy");
        ccLang.get().addDefault("SHOP.ITEM_EDIT_LORE", "§eShiftRMB: Edit");
        ccLang.get().addDefault("SHOP.DECO_DELETE_LORE", "§eShiftRMB: Remove");
        ccLang.get().addDefault("SHOP.PAGE_TITLE", "§f{curPage}/{maxPage} Page");
        ccLang.get().addDefault("SHOP.PAGE_LORE_V2", "§f§nLMB: Previous page\n§f§nRMB: Next page\n§7Clicking on your item will\n§7take you to the page where\n§7that item is located.");
        ccLang.get().addDefault("SHOP.GO_TO_PAGE_EDITOR", "§eShift RMB: Page Editor");
        ccLang.get().addDefault("SHOP.ITEM_MOVE_SELECTED", "Item selected. Right-click on an empty field to move it.");
        ccLang.get().addDefault("SHOP.PERMISSION", "§fPermission:");
        ccLang.get().addDefault("SHOP.PERMISSION_ITEM", "§7 - {permission}");
        ccLang.get().addDefault("SHOP.FLAGS", "§eFlag:");
        ccLang.get().addDefault("SHOP.FLAGS_ITEM", "§e - {flag}");
        ccLang.get().addDefault("SHOP.SHOP_BAL_INF", "§fUnlimited");
        ccLang.get().addDefault("SHOP.SHOP_BAL", "§fShop account balance");
        ccLang.get().addDefault("SHOP.SHOP_LOCATION", "§fShop location: x {x}, y {y}, z {z}");
        ccLang.get().addDefault("SHOP.SHOP_LOCATION_B", "§fShop location: ");
        ccLang.get().addDefault("SHOP.SHOP_INFO_DASH", "§7 - ");
        ccLang.get().addDefault("SHOP.DISABLED", "§cDisabled§8|§f");
        ccLang.get().addDefault("SHOP.INCOMPLETE_DATA", "INCOMPLETE DATA");
        ccLang.get().addDefault("SHOP.INCOMPLETE_DATA_Lore", "This item is not visible\nto non-op users.\nIndex: ");
        ccLang.get().addDefault("SHOP.TRADE_LIMIT_BUY", "§aPurchase limit: {num}left");
        ccLang.get().addDefault("SHOP.TRADE_LIMIT_SELL", "§aSales limit: {num}left");
        ccLang.get().addDefault("SHOP.TRADE_LIMIT_TIMER", "§aNext reset: {time}");
        ccLang.get().addDefault("SHOP.CLICK_TO_ADD", "§eClick: Add");

        ccLang.get().addDefault("SHOP_SETTING_TITLE", "§3Shop Settings");
        ccLang.get().addDefault("SHOP_SETTING.LOG_TOGGLE_LORE", "§eRMB: Log Viewer");
        ccLang.get().addDefault("SHOP_SETTING.LOG_PRINT_CONSOLE", "§fPrint log to console");
        ccLang.get().addDefault("SHOP_SETTING.LOG_PRINT_ADMIN", "§fPrint log to admin");
        ccLang.get().addDefault("SHOP_SETTING.MAX_PAGE", "§fMax page");
        ccLang.get().addDefault("SHOP_SETTING.MAX_PAGE_LORE", "§fSets the maximum page for the shop.");
        ccLang.get().addDefault("SHOP_SETTING.BACKGROUND", "§fBackground color");
        ccLang.get().addDefault("SHOP_SETTING.BACKGROUND_LORE", "§eClick: Change color");
        ccLang.get().addDefault("SHOP_SETTING.L_R_SHIFT", "§eLMB: -1 RMB: +1 Shift: x5");
        ccLang.get().addDefault("SHOP_SETTING.FLAG", "§fFlag");
        ccLang.get().addDefault("SHOP_SETTING.SHOP_SETTINGS_LORE", "§eRMB: Shop Settings");
        ccLang.get().addDefault("SHOP_SETTING.SIGN_SHOP_LORE", "§fOnly accessible via sign.");
        ccLang.get().addDefault("SHOP_SETTING.LOCAL_SHOP_LORE", "§fMust visit actual store locations.");
        ccLang.get().addDefault("SHOP_SETTING.DELIVERY_CHARGE_LORE", "§fYou can pay for delivery without \n§fhaving to go to the shop location to transact.");
        ccLang.get().addDefault("SHOP_SETTING.SELECTED", "§2Selected");
        ccLang.get().addDefault("SHOP_SETTING.CURRENCY", "§fCurrency Type: ");
        ccLang.get().addDefault("SHOP_SETTING.VAULT_LORE", "§fThis is the default.");
        ccLang.get().addDefault("SHOP_SETTING.EXP_LORE", "§fTrade with the player's experience points.");
        ccLang.get().addDefault("SHOP_SETTING.JOB_POINT_LORE", "§fTrade with job points. \n§fRequires 'Jobs Reborn' plugin");
        ccLang.get().addDefault("SHOP_SETTING.PLAYER_POINT_LORE", "§fTrade with player points. \n§fRequires 'Player point' plugin");
        ccLang.get().addDefault("SHOP_SETTING.SHOW_VALUE_CHANGE_LORE", "§fShows the amount of change in price.");
        ccLang.get().addDefault("SHOP_SETTING.HIDE_STOCK", "§fHide stock.");
        ccLang.get().addDefault("SHOP_SETTING.HIDE_PRICING_TYPE", "§fHide price type.");
        ccLang.get().addDefault("SHOP_SETTING.HIDE_SHOP_BALANCE", "§fHide shop account balance.");
        ccLang.get().addDefault("SHOP_SETTING.SHOW_MAX_STOCK", "§fShow max stock.");
        ccLang.get().addDefault("SHOP_SETTING.HIDDEN_IN_COMMAND", "§fDon't show this store\n§fin command autocomplete.");
        ccLang.get().addDefault("SHOP_SETTING.INTEGER_ONLY", "§fThe purchase price will be rounded up.\n§fThe sale price will be rounded down.");
        ccLang.get().addDefault("SHOP_SETTING.PERMISSION", "§fPermission");
        ccLang.get().addDefault("SHOP_SETTING.STATE", "§fState");
        ccLang.get().addDefault("SHOP_SETTING.STATE_ENABLE", "§aEnable");
        ccLang.get().addDefault("SHOP_SETTING.STATE_DISABLE", "§cDisable");
        ccLang.get().addDefault("SHOP_SETTING.ROTATION_EDITOR", "§fRotation: ");
        ccLang.get().addDefault("SHOP_SETTING.ROTATION_EDITOR_LORE", "§eClick: Rotation editor");
        ccLang.get().addDefault("SHOP_SETTING.COMMAND_TOGGLE", "§fCommand");
        ccLang.get().addDefault("SHOP_SETTING.COMMAND_TOGGLE_LORE", "§fExecutes server commands\n§fwhen a store transaction occurs.");
        ccLang.get().addDefault("SHOP_SETTING.COMMAND_SELL", "§fSell Command");
        ccLang.get().addDefault("SHOP_SETTING.COMMAND_BUY", "§fBuy Command");
        ccLang.get().addDefault("SHOP_SETTING.COMMAND_LORE1", "§eLMB: Set");
        ccLang.get().addDefault("SHOP_SETTING.COMMAND_LORE3", "§eShift RMB: Delete last item");
        ccLang.get().addDefault("SHOP_SETTING.TRADE_UI", "§fEdit Trade UI");
        ccLang.get().addDefault("SHOP_SETTING.TRADE_UI_LORE_2", "§eLMB: Edit\n§eRMB: Reset");

        ccLang.get().addDefault("ROTATION_EDITOR_TITLE", "§3Rotation Editor");
        ccLang.get().addDefault("ROTATION_EDITOR.ENABLED", "§aEnabled");
        ccLang.get().addDefault("ROTATION_EDITOR.DISABLED", "§cDisabled");
        ccLang.get().addDefault("ROTATION_EDITOR.CLICK_TO_ENABLE", "§eLMB: Enable");
        ccLang.get().addDefault("ROTATION_EDITOR.CLICK_TO_DISABLE", "§eLMB: Disable");
        ccLang.get().addDefault("ROTATION_EDITOR.CURRENT_TIME", "§fCurrent time: ");
        ccLang.get().addDefault("ROTATION_EDITOR.CURRENTLY_IN_USE", "§a[Currently in use]");
        ccLang.get().addDefault("ROTATION_EDITOR.NEXT_ROTATION", "§fNext rotation: ");
        ccLang.get().addDefault("ROTATION_EDITOR.PERIOD", "§fPeriod");
        ccLang.get().addDefault("ROTATION_EDITOR.PERIOD_LORE_V2", "§eLMB: -1h, RMB: +1h, Shift: x10");
        ccLang.get().addDefault("ROTATION_EDITOR.TIMER", "§fTimer adjustment");
        ccLang.get().addDefault("ROTATION_EDITOR.TIMER_LORE_V2", "§eLMB: -10m, RMB: +10m, Shift: x6");
        ccLang.get().addDefault("ROTATION_EDITOR.HOUR", "§f{0}hours");
        ccLang.get().addDefault("ROTATION_EDITOR.UNSAVED_CHANGES", "§c§oUnsaved changes");
        ccLang.get().addDefault("ROTATION_EDITOR.APPLY_CHANGES", "§fApply changes");
        ccLang.get().addDefault("ROTATION_EDITOR.APPLY_CHANGES_LORE", "§eLMB: Save changes\n§eRMB: Reset Changes");
        ccLang.get().addDefault("ROTATION_EDITOR.APPLY_ROTATION", "§eLMB: Apply now");
        ccLang.get().addDefault("ROTATION_EDITOR.OPEN", "§eLMB: Open");
        ccLang.get().addDefault("ROTATION_EDITOR.CREATE", "§eLMB: Create empty");
        ccLang.get().addDefault("ROTATION_EDITOR.COPY_AS_NEW", "§eRMB: Copy as new");
        ccLang.get().addDefault("ROTATION_EDITOR.DELETE", "§eShift RMB: §cDelete");
        ccLang.get().addDefault("ROTATION_EDITOR.REAPPLY", "§eShift LMB: Reapply");
        ccLang.get().addDefault("ROTATION_EDITOR.MOVE", "§eRMB: Move");

        ccLang.get().addDefault("ITEM_SETTING_TITLE", "§3Item Settings");
        ccLang.get().addDefault("ITEM_SETTING.VALUE_BUY", "§fPurchase value: ");
        ccLang.get().addDefault("ITEM_SETTING.VALUE_SELL", "§fSales value: ");
        ccLang.get().addDefault("ITEM_SETTING.PRICE", "§fBuy: ");
        ccLang.get().addDefault("ITEM_SETTING.SELL_PRICE", "§fSell: ");
        ccLang.get().addDefault("ITEM_SETTING.PRICE_MIN", "§fMinimum price: ");
        ccLang.get().addDefault("ITEM_SETTING.PRICE_MAX", "§fMaximum price: ");
        ccLang.get().addDefault("ITEM_SETTING.MEDIAN", "§fMedian: ");
        ccLang.get().addDefault("ITEM_SETTING.STOCK", "§fStock: ");
        ccLang.get().addDefault("ITEM_SETTING.MAX_STOCK", "§fMax stock: ");
        ccLang.get().addDefault("ITEM_SETTING.MAX_STOCK_LORE", "§fIf the stock exceeds this,\n§fthe shop will refuse to purchase.");
        ccLang.get().addDefault("ITEM_SETTING.INF_STOCK", "Infinite stock");
        ccLang.get().addDefault("ITEM_SETTING.STATIC_PRICE", "Fixed price");
        ccLang.get().addDefault("ITEM_SETTING.UNLIMITED", "Unlimited");
        ccLang.get().addDefault("ITEM_SETTING.MEDIAN_HELP", "§fThe smaller the median,\n§fthe steeper the price change.");
        ccLang.get().addDefault("ITEM_SETTING.TAX_IGNORED", "Sales tax settings are ignored.");
        ccLang.get().addDefault("ITEM_SETTING.RECOMMEND", "§fApply recommended values");
        ccLang.get().addDefault("ITEM_SETTING.DISCOUNT", "§fDiscount");
        ccLang.get().addDefault("ITEM_SETTING.DISCOUNT_LORE", "§fDiscount rate: {num}%\n§eLMB: +10, RMB: -10");
        ccLang.get().addDefault("ITEM_SETTING.DISCOUNT_LORE_2", "§fDiscount rate: {num}%\n§eLMB: -10, RMB: +10");
        ccLang.get().addDefault("ITEM_SETTING.DONE", "§fDone");
        ccLang.get().addDefault("ITEM_SETTING.DONE_LORE", "§fDone!");
        ccLang.get().addDefault("ITEM_SETTING.ROUND_DOWN", "§fRound down");
        ccLang.get().addDefault("ITEM_SETTING.SET_TO_MEDIAN", "§fSet to median");
        ccLang.get().addDefault("ITEM_SETTING.SET_TO_STOCK", "§fSet to stock");
        ccLang.get().addDefault("ITEM_SETTING.SET_TO_VALUE", "§fSet to value");
        ccLang.get().addDefault("ITEM_SETTING.CLOSE", "§fClose");
        ccLang.get().addDefault("ITEM_SETTING.CLOSE_LORE", "§f§nClick: Close");
        ccLang.get().addDefault("ITEM_SETTING.REMOVE", "§cRemove");
        ccLang.get().addDefault("ITEM_SETTING.REMOVE_LORE", "§fRemove this item from the shop.");
        ccLang.get().addDefault("ITEM_SETTING.BUY", "§3§lBuy: {num}");
        ccLang.get().addDefault("ITEM_SETTING.SELL", "§3§lSell: {num}");
        ccLang.get().addDefault("ITEM_SETTING.TRADE_LIMIT_SELL", "§fSales limit per player");
        ccLang.get().addDefault("ITEM_SETTING.TRADE_LIMIT_SELL_LORE", "§f{num}\n§eLMB: -1, RMB: +1, Shift = x10");
        ccLang.get().addDefault("ITEM_SETTING.TRADE_LIMIT_BUY", "§fPurchase limit per player");
        ccLang.get().addDefault("ITEM_SETTING.TRADE_LIMIT_BUY_LORE", "§f{num}\n§eLMB: -1, RMB: +1, Shift = x10");
        ccLang.get().addDefault("ITEM_SETTING.TRADE_LIMIT_INTERVAL", "§fTrading volume limit reset cycle");
        ccLang.get().addDefault("ITEM_SETTING.TRADE_LIMIT_INTERVAL_LORE", "§fInterval: {interval}\n§fNext reset: {time}\n§eLMB: -1h, RMB: +1h, Shift = x12");
        ccLang.get().addDefault("ITEM_SETTING.TRADE_LIMIT_TIMER", "§fAdjust Timer");
        ccLang.get().addDefault("ITEM_SETTING.TRADE_LIMIT_TIMER_LORE", "§f{num}\n§fNext reset: {time}\n§eLMB: -1h, RMB: +1h, Shift = x12");

        ccLang.get().addDefault("TRADE_TITLE", "§3Trade");
        ccLang.get().addDefault("TRADE.TOGGLE_SELLABLE", "§eClick: Sale only toggle");
        ccLang.get().addDefault("TRADE.TOGGLE_BUYABLE", "§eClick: Purchase Only Toggle");
        ccLang.get().addDefault("TRADE.BUY_ONLY_LORE", "§fThis item cannot be sold.");
        ccLang.get().addDefault("TRADE.SELL_ONLY_LORE", "§fThis item cannot be purchased.");
        ccLang.get().addDefault("TRADE.BALANCE", "§3My balance");
        ccLang.get().addDefault("TRADE.PRICE", "§fBuy: {num}");
        ccLang.get().addDefault("TRADE.SELL_PRICE", "§fSell: {num}");
        ccLang.get().addDefault("TRADE.PRICE_DISCOUNTED", "§fBuy: §8§m{num}§r §a{num2}");
        ccLang.get().addDefault("TRADE.SELL_PRICE_DISCOUNTED", "§fSell: §8§m{num}§r §c{num2}");
        ccLang.get().addDefault("TRADE.PRICE_EXP", "§fBuy: {num}EXP");
        ccLang.get().addDefault("TRADE.SELL_PRICE_EXP", "§fSell: {num}EXP");
        ccLang.get().addDefault("TRADE.PRICE_DISCOUNTED_EXP", "§fBuy: §8§m{num}EXP§r §a{num2}EXP");
        ccLang.get().addDefault("TRADE.SELL_PRICE_DISCOUNTED_EXP", "§fSell: §8§m{num}EXP§r §c{num2}EXP");
        ccLang.get().addDefault("TRADE.PRICE_PP", "§fBuy: {num}PP");
        ccLang.get().addDefault("TRADE.SELL_PRICE_PP", "§fSell: {num}PP");
        ccLang.get().addDefault("TRADE.PRICE_DISCOUNTED_PP", "§fBuy: §8§m{num}PP§r §a{num2}PP");
        ccLang.get().addDefault("TRADE.SELL_PRICE_DISCOUNTED_PP", "§fSell: §8§m{num}PP§r §c{num2}PP");
        ccLang.get().addDefault("TRADE.PRICE_JP", "§fBuy: {num}JP");
        ccLang.get().addDefault("TRADE.SELL_PRICE_JP", "§fSell: {num}JP");
        ccLang.get().addDefault("TRADE.PRICE_DISCOUNTED_JP", "§fBuy: §8§m{num}JP§r §a{num2}JP");
        ccLang.get().addDefault("TRADE.SELL_PRICE_DISCOUNTED_JP", "§fSell: §8§m{num}JP§r §c{num2}JP");
        ccLang.get().addDefault("TRADE.BUY", "§cBuy");
        ccLang.get().addDefault("TRADE.SELL", "§2Sell");
        ccLang.get().addDefault("TRADE.STOCK", "§8Stock: ");
        ccLang.get().addDefault("TRADE.STACKS", "§8{num} Stacks");
        ccLang.get().addDefault("TRADE.INF_STOCK", "§8Infinite");
        ccLang.get().addDefault("TRADE.SHOP_BAL_INF", "§fUnlimited");
        ccLang.get().addDefault("TRADE.SHOP_BAL", "§3Shop account balance \n§f{num}");
        ccLang.get().addDefault("TRADE.CLICK_TO_BUY", "§c§nClick: Buy {amount}");
        ccLang.get().addDefault("TRADE.CLICK_TO_SELL", "§2§nClick: Sell {amount}");
        ccLang.get().addDefault("TRADE.PURCHASE_LIMIT_PER_PLAYER", "§aPurchase limit: {num}left\n§aNext reset: {time}");
        ccLang.get().addDefault("TRADE.SALES_LIMIT_PER_PLAYER", "§aSales limit: {num}left\n§aNext reset: {time}");
        ccLang.get().addDefault("TRADE.QUANTITY_LORE", "§eShift RMB: Edit Quantity");
        ccLang.get().addDefault("TRADE.WAIT_FOR_INPUT", "Enter the quantity that will be displayed in the Trade UI.\nExample: 1,2,4,8,16,32,64");

        ccLang.get().addDefault("PAGE_EDITOR_TITLE", "§3Page Editor");
        ccLang.get().addDefault("PAGE_EDITOR.PREV", "§f<<");
        ccLang.get().addDefault("PAGE_EDITOR.NEXT", "§f>>");
        ccLang.get().addDefault("PAGE_EDITOR.PAGE_SWAP_SUCCESS", "§fThe page has been replaced.");
        ccLang.get().addDefault("PAGE_EDITOR.PAGE_SWAP_FAIL", "§fPage replacement failed.");
        ccLang.get().addDefault("PAGE_EDITOR.PAGE_SWAP_SELECTED", "§fPage has been selected. Right-click on the other pages to be replaced.");
        ccLang.get().addDefault("PAGE_EDITOR.PAGE_LORE_PREMIUM", "§eLMB: Open page\n§eRMB: Swap\n§eShift LMB: Insert\n§eShift RMB: Delete");
        ccLang.get().addDefault("PAGE_EDITOR.PRICE", "§fBuy: {num}");
        ccLang.get().addDefault("PAGE_EDITOR.SELL_PRICE", "§fSell: {num}");
        ccLang.get().addDefault("PAGE_EDITOR.STOCK", "§8Stock: {num}");
        ccLang.get().addDefault("PAGE_EDITOR.STACKS", "§8{num} Stakcs");
        ccLang.get().addDefault("PAGE_EDITOR.STATIC_PRICE", "§8[Fixed price]");
        ccLang.get().addDefault("PAGE_EDITOR.EMPTY", "§8(empty)");
        ccLang.get().addDefault("PAGE_EDITOR.EMPTY_SLOT_LORE", "§eLMB,RMB: Push\n§e+Shift: Pull");

        ccLang.get().addDefault("LOG_VIEWER_TITLE", "§3Log Viewer");
        ccLang.get().addDefault("LOG_VIEWER.DATE", "§fDate: ");
        ccLang.get().addDefault("LOG_VIEWER.TIME", "§fTime: ");
        ccLang.get().addDefault("LOG_VIEWER.CURRENCY", "§fCurrency: ");
        ccLang.get().addDefault("LOG_VIEWER.PRICE", "§fPrice: ");
        ccLang.get().addDefault("LOG_VIEWER.EXPAND", "§fExpand");
        ccLang.get().addDefault("LOG_VIEWER.COLLAPSE", "§fCollapse");
        ccLang.get().addDefault("LOG_VIEWER.PAGE_TITLE", "§f{curPage}/{maxPage} Page");
        ccLang.get().addDefault("LOG_VIEWER.PAGE_LORE", "§eLMB: Prev\n§eRMB: Next");
        ccLang.get().addDefault("LOG_VIEWER.FILE_LORE", "§eLMB: Open\n§eShift RMB: §cDelete");

        ccLang.get().addDefault("LOG.LOG", "§fLog");
        ccLang.get().addDefault("LOG.CLEAR", "§fLog deleted");
        ccLang.get().addDefault("LOG.SAVE", "§fLog saved");
        ccLang.get().addDefault("LOG.DELETE", "§4Delete log");
        ccLang.get().addDefault("LOG.SELL", "§f{player} sells {amount} {item} to {shop}");
        ccLang.get().addDefault("LOG.BUY", "§f{player} buys {amount} {item} from {shop}");

        ccLang.get().addDefault("STOCK_SIMULATOR_TITLE", "§3Stock Simulator");
        ccLang.get().addDefault("STOCK_SIMULATOR.CHANGE_SAMPLE_LORE", "§eLMB, RMB: Change Item");
        ccLang.get().addDefault("STOCK_SIMULATOR.SIMULATOR_BUTTON_LORE", "§eRMB: Simulator");
        ccLang.get().addDefault("STOCK_SIMULATOR.RUN_TITLE", "§fRun");
        ccLang.get().addDefault("STOCK_SIMULATOR.RUN_LORE", "§eLMB: Run simulation\n§eRMB: Apply the settings to the shop\n§fItems are not affected.");
        ccLang.get().addDefault("STOCK_SIMULATOR.REAL_TIME", "§a(real time)");
        ccLang.get().addDefault("STOCK_SIMULATOR.AFTER_S", "§aAfter {0} seconds");
        ccLang.get().addDefault("STOCK_SIMULATOR.AFTER_M", "§aAfter {0} minutes");
        ccLang.get().addDefault("STOCK_SIMULATOR.AFTER_H", "§aAfter {0} hours");
        ccLang.get().addDefault("STOCK_SIMULATOR.AFTER_D", "§aAfter {0} days");
        ccLang.get().addDefault("STOCK_SIMULATOR.L_R_SHIFT", "§eLMB: -1 RMB: +1 Shift: x5");
        ccLang.get().addDefault("STOCK_SIMULATOR.PRICE", "§fPurchase price: {num}");
        ccLang.get().addDefault("STOCK_SIMULATOR.MEDIAN", "§fMedian: {num}");
        ccLang.get().addDefault("STOCK_SIMULATOR.STOCK", "§fStock: {num}");

        ccLang.get().addDefault("PALETTE_TITLE", "§3Select item to sell");
        ccLang.get().addDefault("PALETTE_TITLE2", "§3Select item");
        ccLang.get().addDefault("PALETTE.LORE_PREMIUM", "§eLMB: Add\n§eShift LMB: Add after setting\n§eRMB: Add as decoration\n§eShift RMB: Search '{item}'");
        ccLang.get().addDefault("PALETTE.LORE2", "§eLMB: Select\n§eShift RMB: Search '{item}'");
        ccLang.get().addDefault("PALETTE.SEARCH", "§fSearch");
        ccLang.get().addDefault("PALETTE.ADD_ALL", "§fAdd all");
        ccLang.get().addDefault("PALETTE.ADD_ALL_LORE", "§eLMB: Add all\n§eShift LMB: Add all and apply recommended values");
        ccLang.get().addDefault("PALETTE.PAGE_TITLE", "§f{curPage}/{maxPage} page");
        ccLang.get().addDefault("PALETTE.PAGE_LORE", "§f§nLMB: Prev\n§f§nRMB: Next");
        ccLang.get().addDefault("PALETTE.FILTER_APPLIED", "§fFilter Applied : ");
        ccLang.get().addDefault("PALETTE.FILTER_LORE", "§eLMB: Search\n§eRMB: Clear filter\n\n§7Example for finding \"BLUE_WOOL\":\n§7 b w\n§7 wool\n§7 blue wool");

        ccLang.get().addDefault("QUICK_SELL_TITLE", "§3Quick Sell");
        ccLang.get().addDefault("QUICK_SELL.GUIDE_TITLE", "§3§lQuick Sell Guide");
        ccLang.get().addDefault("QUICK_SELL.GUIDE_LORE", "§aLeft-click the item you want to sell.\n§aShift left click to sell all items of the same type.\n§aRight-click to go to the item shop.");

        ccLang.get().addDefault("ARROW.UP", "§a⬆");
        ccLang.get().addDefault("ARROW.DOWN", "§c⬇");
        ccLang.get().addDefault("ARROW.UP_2", "§c⬆");
        ccLang.get().addDefault("ARROW.DOWN_2", "§a⬇");

        ccLang.get().addDefault("TIME.OPEN", "Open");
        ccLang.get().addDefault("TIME.CLOSE", "Close");
        ccLang.get().addDefault("TIME.OPEN_LORE", "§fSet opening time");
        ccLang.get().addDefault("TIME.CLOSE_LORE", "§fSet closing time");
        ccLang.get().addDefault("TIME.SHOPHOURS", "§fOpening hours");
        ccLang.get().addDefault("TIME.OPEN24", "Open 24 hours");
        ccLang.get().addDefault("TIME.SHOP_IS_CLOSED", "§fThe shop is closed. It opens at {time} o'clock. {curTime} o'clock now.");
        ccLang.get().addDefault("TIME.SET_SHOPHOURS", "Set business hours");
        ccLang.get().addDefault("TIME.CUR", "§fCurrent time: {time}h");

        ccLang.get().addDefault("STOCK_STABILIZING.SS", "§fStock stabilization");
        ccLang.get().addDefault("STOCK_STABILIZING.L_R_SHIFT", "§eLMB: -0.1 RMB: +0.1 Shift: x5");
        ccLang.get().addDefault("STOCK_STABILIZING.STRENGTH_LORE_A", "§fn% of median");
        ccLang.get().addDefault("STOCK_STABILIZING.STRENGTH_LORE_B", "§fn% of the gap with median");

        ccLang.get().addDefault("FLUCTUATION.FLUCTUATION", "§fStock fluctuation");
        ccLang.get().addDefault("FLUCTUATION.INTERVAL", "§fInterval");
        ccLang.get().addDefault("FLUCTUATION.INTERVAL_LORE", "§f1h = 1000ticks = real time 50s");
        ccLang.get().addDefault("FLUCTUATION.STRENGTH", "§fStrength");
        ccLang.get().addDefault("FLUCTUATION.STRENGTH_LORE", "§fn% of median");

        ccLang.get().addDefault("TAX.SALES_TAX", "§fSales tax");
        ccLang.get().addDefault("TAX.USE_GLOBAL", "Use global settings ({tax}%)");
        ccLang.get().addDefault("TAX.USE_LOCAL", "Set separately");

        ccLang.get().addDefault("MESSAGE.SEARCH_ITEM", "§fEnter the name of the item you are looking for.");
        ccLang.get().addDefault("MESSAGE.SEARCH_CANCELED", "§fSearch Canceled.");
        ccLang.get().addDefault("MESSAGE.INPUT_CANCELED", "§fInput canceled.");
        ccLang.get().addDefault("MESSAGE.DELETE_CONFIRM", "§fAre you sure you want to delete the page? Enter 'delete' to delete.");
        ccLang.get().addDefault("MESSAGE.CANT_DELETE_LAST_PAGE", "§fThe last remaining page cannot be deleted.");
        ccLang.get().addDefault("MESSAGE.SHOP_BAL_LOW", "§fThe shop doesn't have enough money.");
        ccLang.get().addDefault("MESSAGE.SHOP_CREATED", "§fShop created!");
        ccLang.get().addDefault("MESSAGE.SHOP_DELETED", "§fShop deleted!");
        ccLang.get().addDefault("MESSAGE.OUT_OF_STOCK", "§fOut of stock!");
        ccLang.get().addDefault("MESSAGE.BUY_SUCCESS", "§fBought {item} x{amount} for {price}. Balance: {bal}");
        ccLang.get().addDefault("MESSAGE.SELL_SUCCESS", "§fSold {item} x{amount} for {price}. Balance: {bal}");
        ccLang.get().addDefault("MESSAGE.BUY_SUCCESS_EXP", "§fBought {item} x{amount} for {price}Exp Points. Balance: {bal}");
        ccLang.get().addDefault("MESSAGE.SELL_SUCCESS_EXP", "§fSold {item} x{amount} for {price}Exp Points. Balance: {bal}");
        ccLang.get().addDefault("MESSAGE.BUY_SUCCESS_JP", "§fBought {item} x{amount} for {price}points. Remaining points: {bal}");
        ccLang.get().addDefault("MESSAGE.SELL_SUCCESS_JP", "§fSold {item} x{amount} for {price}points. Remaining points: {bal}");
        ccLang.get().addDefault("MESSAGE.BUY_SUCCESS_PP", "§fBought {item} x{amount} for {price}points. Remaining points: {bal}");
        ccLang.get().addDefault("MESSAGE.SELL_SUCCESS_PP", "§fSold {item} x{amount} for {price}points. Remaining points: {bal}");
        ccLang.get().addDefault("MESSAGE.QSELL_NA", "§fThere is no shop that handles this item.");
        ccLang.get().addDefault("MESSAGE.DELIVERY_CHARGE", "§fDelivery fee: {fee}");
        ccLang.get().addDefault("MESSAGE.DELIVERY_CHARGE_NA", "§fIt cannot be delivered to another world.");
        ccLang.get().addDefault("MESSAGE.NOT_ENOUGH_MONEY", "§fNot enough money. balance: {bal}");
        ccLang.get().addDefault("MESSAGE.NOT_ENOUGH_POINT", "§fNot enough points. balance: {bal}");
        ccLang.get().addDefault("MESSAGE.NOT_ENOUGH_PLAYER_POINT", "§fNot enough player points. balance: {bal}");
        ccLang.get().addDefault("MESSAGE.NOT_ENOUGH_EXP_POINT", "§fNot enough Exp points. balance: {bal}");
        ccLang.get().addDefault("MESSAGE.NO_ITEM_TO_SELL", "§fThere are no items for sale.");
        ccLang.get().addDefault("MESSAGE.NO_ITEM_TO_SELL_2", "§fThere are no items available for sale.");
        ccLang.get().addDefault("MESSAGE.INVENTORY_FULL", "§4There are no empty spaces in your inventory!");
        ccLang.get().addDefault("MESSAGE.IRREVERSIBLE", "§fThis action is irreversible!");
        ccLang.get().addDefault("MESSAGE.ITEM_ADDED", "Item added!");
        ccLang.get().addDefault("MESSAGE.ITEM_UPDATED", "Item updated!");
        ccLang.get().addDefault("MESSAGE.ITEM_DELETED", "Item deleted!");
        ccLang.get().addDefault("MESSAGE.CHANGES_APPLIED", "Changes applied. New values:");
        ccLang.get().addDefault("MESSAGE.CHANGES_APPLIED_2", "Changes applied");
        ccLang.get().addDefault("MESSAGE.RECOMMEND_APPLIED", "Recommended value applied. It is based on {playerNum}players. You can change this value in the config file.");
        ccLang.get().addDefault("MESSAGE.TRANSFER_SUCCESS", "Remittance completed");
        ccLang.get().addDefault("MESSAGE.PURCHASE_REJECTED", "There are too many of these items in the shop. Can't sell it now.");
        ccLang.get().addDefault("MESSAGE.CLICK_YOUR_ITEM_START_PAGE", "Click on an item in your inventory to find the shop with the best deal.\nLMB: Buy   RMB: Sell");
        ccLang.get().addDefault("MESSAGE.MOVE_TO_BEST_SHOP_BUY", "Moved to the shop where you can buy {item} at the lowest price.");
        ccLang.get().addDefault("MESSAGE.MOVE_TO_BEST_SHOP_SELL", "Moved to the shop where you can sell {item} at the highest price.");
        ccLang.get().addDefault("MESSAGE.SHOP_IS_CLOSED_BY_ADMIN", "This shop is currently closed by the server administrator.");
        ccLang.get().addDefault("MESSAGE.SHOP_DISABLED", "This shop is currently disabled. Non-admin users cannot use it. You can enable it in the shop settings.");
        ccLang.get().addDefault("MESSAGE.ROTATION_SHARED_DATA_MISSING", "§e[ ! ]§fThe required data could not be found in '{0}/SharedData.yml' for {1} items. These items have been added as decoration.");
        ccLang.get().addDefault("MESSAGE.ENTER_COMMAND", "Enter the command without '/'.");
        ccLang.get().addDefault("MESSAGE.ENTER_COMMAND_2", "Enter in the form of 'index/command'.");
        ccLang.get().addDefault("MESSAGE.SELL_COMMAND_CUR", "Sell commands currently in effect:");
        ccLang.get().addDefault("MESSAGE.BUY_COMMAND_CUR", "Buy commands currently in effect:");
        ccLang.get().addDefault("MESSAGE.Q_SEARCH_FAIL_CURRENCY", "This item is being traded in several currencies.");

        ccLang.get().addDefault("HELP.TITLE", "§fHelp: {command} --------------------");
        ccLang.get().addDefault("HELP.SHOP", "Open shop");
        ccLang.get().addDefault("HELP.CMD", "Toggle display of command help.");
        ccLang.get().addDefault("HELP.CREATE_SHOP", "Create a new shop.");
        ccLang.get().addDefault("HELP.CREATE_SHOP_2", "Permissions (can be changed later)\n   true: dshop.user.shop.shopName\n   false: Anyone can access (default)\n   Arbitrary value: Requires permission");
        ccLang.get().addDefault("HELP.DELETE_SHOP", "Remove existing stores.");
        ccLang.get().addDefault("HELP.SHOP_ADD_HAND", "Adds the item in hand to the shop.");
        ccLang.get().addDefault("HELP.SHOP_ADD_ITEM", "Add item to the shop.");
        ccLang.get().addDefault("HELP.SHOP_EDIT", "Edit item in the store.");
        ccLang.get().addDefault("HELP.PRICE", "§7Price calculation formula: median*value/stock");
        ccLang.get().addDefault("HELP.INF_STATIC", "§7median<0 == Fixed price     stock<0 == Infinite stock");
        ccLang.get().addDefault("HELP.EDIT_ALL", "Modify all items in the shop at once.");
        ccLang.get().addDefault("HELP.RELOAD", "Reload the plugin.");
        ccLang.get().addDefault("HELP.RELOADED", "Plugin reloaded");
        ccLang.get().addDefault("HELP.USAGE", "Usage");
        ccLang.get().addDefault("HELP.ITEM_ALREADY_EXIST", "§7§o{item} is already on sale.\n   {info}\n   Entering a command modifies the value.");
        ccLang.get().addDefault("HELP.ITEM_INFO", "§7§o{item}'s current settings:\n   {info}");
        ccLang.get().addDefault("HELP.REMOVE_ITEM", "§fEntering an argument of 0 will §4remove§f this item from the store.");
        ccLang.get().addDefault("HELP.QSELL", "§fSell items quickly.");
        ccLang.get().addDefault("HELP.DELETE_OLD_USER", "Delete long-term inactive user data");
        ccLang.get().addDefault("HELP.ACCOUNT", "Sets the account balance of the shop. -1 = unlimited");
        ccLang.get().addDefault("HELP.SET_TO_REC_ALL", "§cResets§e all item settings in the store to the recommended values.");
        ccLang.get().addDefault("HELP.SHOP_ENABLE", "Enables or disables the shop.");

        ccLang.get().addDefault("ERR.NO_USER_ID", "§6Player uuid not found. Shop unavailable.");
        ccLang.get().addDefault("ERR.ITEM_NOT_EXIST", "The item does not exist in the store.");
        ccLang.get().addDefault("ERR.ITEM_FORBIDDEN", "This is a prohibited item.");
        ccLang.get().addDefault("ERR.NO_PERMISSION", "§eYou do not have permission.");
        ccLang.get().addDefault("ERR.WRONG_USAGE", "Incorrect command usage.");
        ccLang.get().addDefault("ERR.NO_EMPTY_SLOT", "There is no empty space in the shop.");
        ccLang.get().addDefault("ERR.WRONG_DATATYPE", "Invalid argument type");
        ccLang.get().addDefault("ERR.VALUE_ZERO", "The argument value must be greater than 0.");
        ccLang.get().addDefault("ERR.WRONG_ITEM_NAME", "Invalid item name.");
        ccLang.get().addDefault("ERR.HAND_EMPTY", "You must hold the item in your hand.");
        ccLang.get().addDefault("ERR.HAND_EMPTY2", "§c§oYou must have the item in your hand!");
        ccLang.get().addDefault("ERR.SHOP_NOT_FOUND", "§fThe shop could not be found.");
        ccLang.get().addDefault("ERR.SHOP_EXIST", "A store with that name already exists.");
        ccLang.get().addDefault("ERR.SHOP_NOT_EXIST", "No shop with that name.");
        ccLang.get().addDefault("ERR.SIGN_SHOP_REMOTE_ACCESS", "The shop is only accessible via sign.");
        ccLang.get().addDefault("ERR.LOCAL_SHOP_REMOTE_ACCESS", "The shop can only be used by visiting it in person.");
        ccLang.get().addDefault("ERR.MAX_LOWER_THAN_MIN", "The maximum price must be greater than the minimum price.");
        ccLang.get().addDefault("ERR.DEFAULT_VALUE_OUT_OF_RANGE", "The base price must be between the minimum price and the maximum price.");
        ccLang.get().addDefault("ERR.NO_RECOMMEND_DATA", "There is no information for this item in the Worth.yml file.");
        ccLang.get().addDefault("ERR.JOBS_REBORN_NOT_FOUND", "Could not find 'Jobs reborn'.");
        ccLang.get().addDefault("ERR.PLAYER_POINTS_NOT_FOUND", "Could not find 'Player points'.");
        ccLang.get().addDefault("ERR.SHOP_HAS_INF_BAL", "{shop} is an infinite account store.");
        ccLang.get().addDefault("ERR.SHOP_DIFF_CURRENCY", "The two stores use different currencies.");
        ccLang.get().addDefault("ERR.PLAYER_NOT_EXIST", "The player could not be found.");
        ccLang.get().addDefault("ERR.SHOP_LINK_FAIL", "Either store must be a real account.");
        ccLang.get().addDefault("ERR.SHOP_LINK_TARGET_ERR", "The target store must have a real account.");
        ccLang.get().addDefault("ERR.NESTED_STRUCTURE", "It is forbidden to build hierarchies. (ex. aa-bb, bb-cc)");
        ccLang.get().addDefault("ERR.CREATIVE", "§eYou cannot use this command in Creative mode. You do not have permission.");
        ccLang.get().addDefault("ERR.FILE_CREATE_FAIL", "§eFile creation failed");
        ccLang.get().addDefault("ERR.INVALID_TRANSACTION", "This transaction is no longer valid. If this problem recurs, contact your server administrator");
        ccLang.get().addDefault("ERR.SIGN_WALL", "Sign must be placed on wall.");

        ccLang.get().addDefault("ERR.SHOP_NULL", "§eShop name cannot be null.");
        ccLang.get().addDefault("ERR.ITEMINFO_HAND_EMPTY", "You need to hold an item.");
        ccLang.get().addDefault("HELP.ITEMINFO_USAGE", "§fHold an item in your hand to learn about it.");
        ccLang.get().addDefault("HELP.ITEMINFO_REALNAME", "§7Real name: §3{item_realname}");
        ccLang.get().addDefault("HELP.ITEMINFO_SIGN_NAME", "§7Sign name: §3{item_signname}");
        ccLang.get().addDefault("ERR.SIGN_ITEM_INVALID", "Invalid item. Use /ds iteminfo to find the name of the item on the sign.");
        ccLang.get().addDefault("ERR.SIGN_ITEM_NOT_FOR_SALE", "First you must add the item in the shop and then use it on the sign.");
        ccLang.get().addDefault("MESSAGE.SIGN_SHOP_CREATED", "§aSign Shop created!");

        ccLang.get().addDefault("ON", "ON");
        ccLang.get().addDefault("OFF", "OFF");
        ccLang.get().addDefault("SET", "SET");
        ccLang.get().addDefault("UNSET", "UNSET");
        ccLang.get().addDefault("NULL", "Null");
        ccLang.get().addDefault("NULL(OPEN)", "None (open to all)");
        ccLang.get().addDefault("CUR_STATE", "Current Status");
        ccLang.get().addDefault("CLICK", "Click");
        ccLang.get().addDefault("LMB", "LMB");
        ccLang.get().addDefault("RMB", "RMB");
        ccLang.get().addDefault("CLOSE", "§fClose");
        ccLang.get().addDefault("CLOSE_LORE", "§f§nClick: Close");

        ccLang.get().addDefault("EXP_POINTS", "Exp Points");
        ccLang.get().addDefault("JOB_POINTS", "Job Points");
        ccLang.get().addDefault("PLAYER_POINTS", "Player Points");

        ccLang.get().options().copyDefaults(true);
        ccLang.save();
    }

    public static final Pattern HEX_PATTERN = Pattern.compile("(#[A-Fa-f0-9]{6})");

    public static String t(Player player, String key)
    {
        return t(player, key, true);
    }

    public static String t(CommandSender sender, String key)
    {
        Player player = null;
        if(sender instanceof Player)
            player = (Player) sender;

        return t(player, key, true);
    }

    public static String t(Player player, String key, boolean hexConvert)
    {
        String temp = ccLang.get().getString(key);
        if(temp == null || temp.isEmpty())
            return key;

        if (hexConvert && ConfigUtil.GetUseHexColorCode())
        {
            Matcher matcher = HEX_PATTERN.matcher(temp);
            while (matcher.find())
            {
                temp = temp.replace(matcher.group(), String.valueOf(ChatColor.of(matcher.group())));
            }
        }

        if(player != null && DynamicShop.isPapiExist && ConfigUtil.GetUsePlaceholderAPI())
            return PlaceholderAPI.setPlaceholders(player, temp);
        else
            return temp;
    }

    public static String TranslateHexColor(String message)
    {
        Matcher matcher = HEX_PATTERN.matcher(message);
        while (matcher.find())
        {
            message = message.replace(matcher.group(), String.valueOf(ChatColor.of(matcher.group())));
        }
        return message;
    }

    public static boolean sendMessageWithLocalizedItemName(Player player, String message, Material material) {
        if (material != null) {
            String matKey;
            try {
                matKey = DynamicShop.localeManager.queryMaterial(material, (short)0, null);
            } catch (Exception var8) {
                Bukkit.getLogger().severe("[LocaleLib] Unable to query Material: " + material.name());
                return false;
            }

            String[] splitByRegex = null;
            if(ConfigUtil.GetUseHexColorCode())
                splitByRegex = HEX_PATTERN.split(message);

            if(splitByRegex != null && splitByRegex.length > 1)
            {
                StringBuilder finalString;
                if(splitByRegex[0].contains("<item>"))
                {
                    String[] splitByItem = splitByRegex[0].split("<item>");
                    finalString = new StringBuilder(("{\"text\":\"" + splitByItem[0] + "\"},"));
                    finalString.append("{\"translate\":\"").append(matKey).append("\"},");
                    finalString.append("{\"text\":\"").append(splitByItem[1]).append("\"},");
                }
                else
                {
                    finalString = new StringBuilder("{\"text\":\"" + splitByRegex[0] + "\"},");
                }

                int idx = 0;

                Matcher matcher = HEX_PATTERN.matcher(message);

                while (matcher.find())
                {
                    if(splitByRegex[idx+1].contains("<item>"))
                    {
                        String[] splitByItem = splitByRegex[idx+1].split("<item>");
                        finalString.append("{\"text\":\"").append(splitByItem[0]).append("\", \"color\":\"").append(matcher.group()).append("\"},");
                        finalString.append("{\"translate\":\"").append(matKey).append("\", \"color\":\"").append(matcher.group()).append("\"},");
                        finalString.append("{\"text\":\"").append(splitByItem[1]).append("\", \"color\":\"").append(matcher.group()).append("\"}");
                    }
                    else
                    {
                        finalString.append("{\"text\":\"").append(splitByRegex[idx + 1]).append("\", \"color\":\"").append(matcher.group()).append("\"}");
                    }

                    idx++;
                    if(idx < splitByRegex.length - 1)
                        finalString.append(",");
                }

                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tellraw " + player.getName() + " [" + finalString + "]");
            }
            else
            {
                String replacement = "\",{\"translate\":\"" + matKey + "\"";

                String text = message.split("<item>")[0];
                if (text.contains("§")) {
                    String colorCode = org.bukkit.ChatColor.getLastColors(text).replace("§", "");
                    if (org.bukkit.ChatColor.getByChar(colorCode) != null) {
                        String colorName = org.bukkit.ChatColor.getByChar(colorCode).name();
                        replacement = replacement + ", \"color\":\"" + colorName.toLowerCase() + "\"";
                    }
                }
                replacement = replacement + "},\"";

                String msg = message.replace("<item>", replacement);
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tellraw " + player.getName() + " [\"" + msg + "\"]");
            }

            return true;
        } else {
            return false;
        }
    }

    private static void ReloadNumberFormat()
    {
        intFormat = new DecimalFormat(ConfigUtil.GetIntFormat());
        doubleFormat = new DecimalFormat(ConfigUtil.GetDoubleFormat());
    }

    private static DecimalFormat intFormat;
    private static DecimalFormat doubleFormat;

    public static String n(int i)
    {
        return intFormat.format(i);
    }

    public static String n(double i)
    {
        return doubleFormat.format(i);
    }

    public static String n(double i, boolean toInt)
    {
        if (toInt)
            return intFormat.format((int)i);
        else
            return doubleFormat.format(i);
    }
}
