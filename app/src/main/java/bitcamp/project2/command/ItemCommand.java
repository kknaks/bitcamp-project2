package bitcamp.project2.command;

import bitcamp.project2.App;
import bitcamp.project2.vo.Items;
import bitcamp.project2.vo.ToDoList;

public class ItemCommand {

    // ItemCommand ToDoList & Items 반환
    private static Items items;
    private ToDoList toDoList;

    public ItemCommand(Items items) {
        this.items = items;
    }

    // //아이템 리스트 받기
    //public void makeItemMenuList(){
    //    String[] itemMenuList = new String[App.subMenus[1].length];
    //
    //    for(int i = 0; i < App.subMenus[1].length; i++){
    //        itemMenuList[i] = App.subMenus[1][i];
    //    }
    //}

    // 출력용 Line
    public static String line = "----------------------------------";

    public static void printItemMenus(String menuTitle, String[] menus){
        String boldAnsi = "\033[1m";
        String resetAnsi = "\033[0m";
        String yellowAnsi = "\033[93m";
        String appTitle = "             [아이템]";
        System.out.println(boldAnsi + line + resetAnsi);
        System.out.println(boldAnsi + appTitle + resetAnsi);
        System.out.println(boldAnsi + line + resetAnsi);
        if (menuTitle.equals("상점가기"))
        {
            System.out.printf("1.지각방지.......%10d 골드\n", ShopCommand.priceLateCoupon);
            System.out.printf("2.졸음방지.......%10d 골드\n", ShopCommand.priceSleepCoupon);
            System.out.printf("3.복습했다치기...%10d 골드\n", ShopCommand.priceStudyCoupon);
            System.out.printf("4.야자출튀.......%10d 골드\n", ShopCommand.priceNightCoupon);
            System.out.println(boldAnsi + line + resetAnsi);
        }
        printItemInventory();
        System.out.println(boldAnsi + line + resetAnsi);
        System.out.println("9. 이전");
        System.out.println(boldAnsi + line + resetAnsi);
    }

    // 메뉴실행
    public void executeItemCommand(String subTitle, ToDoList toDoList) {

        switch (subTitle){
            case "지각방지":
                useLateCoupon(subTitle,toDoList);
                break;
            case "졸음방지":
                useSleepCoupon(subTitle,toDoList);
                break;
            case "복습했다치기":
                useStudyCoupon(subTitle,toDoList);
                break;
            case "야자출튀":
                useNightCoupon(subTitle,toDoList);
                break;
        }

    }

    // 아이템(쿠폰)사용
    private void useLateCoupon(String subTitle, ToDoList toDoList) {
        if (toDoList.isLate()){
            System.out.println("이미 달성하여 사용할 수 없습니다.");
        } else {
            toDoList.setLate(true);
            checkItem(subTitle);
        }
        printItemList(toDoList);
    }

    private void useSleepCoupon(String subTitle, ToDoList toDoList) {
        if (toDoList.isSleep()){
            System.out.println("이미 달성하여 사용할 수 없습니다.");
        } else {
            toDoList.setSleep(true);
            checkItem(subTitle);
        }
        printItemList(toDoList);
    }

    private void useStudyCoupon(String subTitle, ToDoList toDoList) {
        if (toDoList.isStudy()){
            System.out.println("이미 달성하여 사용할 수 없습니다.");
        } else {
            toDoList.setStudy(true);
            checkItem(subTitle);
        }
        printItemList(toDoList);
    }

    private void useNightCoupon(String subTitle, ToDoList toDoList) {
        if (toDoList.isNight()){
            System.out.println("이미 달성하여 사용할 수 없습니다.");
        } else {
            toDoList.setNight(true);
            checkItem(subTitle);
        }
        printItemList(toDoList);
    }

    // 아이템체크
    public void checkItem(String subTitle){
        String blueAnsi = "\033[94m";
        String boldAnsi = "\033[1m";
        String resetAnsi = "\033[0m";
        String ansiSubTitle = (blueAnsi+boldAnsi+subTitle+resetAnsi);

        switch (subTitle){
            case "지각방지":
                if(items.getLateCoupon() == 0) {
                    System.out.println("아이템이 없습니다.");
                    break;
                }else {
                    items.decrementCoupon(subTitle);
                    System.out.printf("[%s]을(를) 사용하였습니다.\n", ansiSubTitle);
                }
                break;
            case "졸음방지":
                if(items.getSleepCoupon() == 0) {
                    System.out.println("아이템이 없습니다.");
                    break;
                }else {
                    items.decrementCoupon(subTitle);
                    System.out.printf("[%s]을(를) 사용하였습니다.\n", ansiSubTitle);
                }
                break;
            case "복습했다치기":
                if(items.getStudyCoupon() == 0) {
                    System.out.println("아이템이 없습니다.");
                    break;
                }else {
                    items.decrementCoupon(subTitle);
                    System.out.printf("[%s]을(를) 사용하였습니다.\n", ansiSubTitle);
                }
                break;
            case "야자출튀":
                if(items.getNightCoupon() == 0) {
                    System.out.println("아이템이 없습니다.");
                    break;
                }else {
                    items.decrementCoupon(subTitle);
                    System.out.printf("[%s]을(를) 사용하였습니다.\n", ansiSubTitle);
                }
                break;
        }
    }


    public static void printItemInventory() {
        System.out.println("[아이템 리스트]");
        System.out.printf("1.지각방지.......%4d 개\n", items.getLateCoupon());
        System.out.printf("2.졸음방지.......%4d 개\n", items.getSleepCoupon());
        System.out.printf("3.복습했다치기...%4d 개\n", items.getStudyCoupon());
        System.out.printf("4.야자출튀.......%4d 개\n", items.getNightCoupon());
        System.out.println(line);
        printGold();
    }

    public static void printGold(){
        String boldAnsi = "\033[1m";
        String resetAnsi = "\033[0m";
        String yellowAnsi = "\033[93m";
        String goldString = (boldAnsi + yellowAnsi + items.getGold() + resetAnsi);
        System.out.printf("현재 보유골드는 [ %s ] 입니다. \n", goldString);
    }

    // 현재할일현황 변경
    public void printItemList(ToDoList toDoList){
        System.out.println(line);
        System.out.println("현재 할일 현황");
        System.out.printf("%s노 지 각 :  %s%s\n", (toDoList.isLate() ? "\033[94m" : "\033[31m"),
            (toDoList.isLate() ? "완료" : "실패"), "\033[0m");
        System.out.printf("%s노 졸 음 :  %s%s\n", (toDoList.isSleep() ? "\033[94m" : "\033[31m"),
            (toDoList.isSleep() ? "완료" : "실패"), "\033[0m");
        System.out.printf("%s복    습 :  %s%s\n", (toDoList.isStudy() ? "\033[94m" : "\033[31m"),
            (toDoList.isStudy() ? "완료" : "실패"), "\033[0m");
        System.out.printf("%s야    자 :  %s%s\n", (toDoList.isNight() ? "\033[94m" : "\033[31m"),
            (toDoList.isNight() ? "완료" : "실패"), "\033[0m");
        System.out.println(line);
        printItemInventory();
        System.out.println(line);

    }
}
