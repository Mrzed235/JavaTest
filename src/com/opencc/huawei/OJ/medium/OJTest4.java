package com.opencc.huawei.OJ.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 手机App防沉迷系统
 * <a href="https://www.nowcoder.com/discuss/606409792084537344?sourceSSR=search">...</a>
 * 题目描述
 * 智能手机方便了我们生活的同时，也侵占了我们不少的时间。“手机App防沉迷系统”能够让我们每天合理地规划手机App使用时间，在正确的时间做正确的事。
 * 它的大概原理是这样的：
 * 在一天24小时内，可以注册每个App的允许使用时段
 * 一个时间段只能使用一个App
 * App有优先级，数值越高，优先级越高。注册使用时段时，如果高优先级的App时间和低优先级的时段有冲突，则系统会自动注销低优先级的时段，如果App的优先级相同，则后添加的App不能注册。
 * 请编程实现，根据输入数据注册App，并根据输入的时间点，返回时间点使用的App名称，如果该时间点没有注册任何App，请返回字符串“NA”。
 * 输入描述
 * 第一行表示注册的App数量 N（N ≤ 100）
 * 第二部分包括 N 行，每行表示一条App注册数据
 * 最后一行输入一个时间点，程序即返回该时间点使用的App
 * 2
 * App1 1 09:00 10:00
 * App2 2 11:00 11:30
 * 09:30
 * 数据说明如下：
 * N行注册数据以空格分隔，四项数依次表示：App名称、优先级、起始时间、结束时间
 * 优先级1~5，数字越大，优先级越高
 * 时间格式 HH:MM，小时和分钟都是两位，不足两位前面补0
 * 起始时间需小于结束时间，否则注册不上
 * 注册信息中的时间段包含起始时间点，不包含结束时间点
 */
class AppInfo {
    String name;
    int priority;
    int startTime;
    int endTime;

    public AppInfo(String name, int priority, String startTime, String endTime) {
        this.name = name;
        this.priority = priority;
        this.startTime = timeToInt(startTime);
        this.endTime = timeToInt(endTime);
    }

    public static int timeToInt(String time) {
        String[] times = time.split(":");
        int hours = Integer.parseInt(times[0]);
        int minutes = Integer.parseInt(times[1]);
        return hours * 60 + minutes;
    }
}

public class OJTest4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();
        List<AppInfo> apps = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] data = in.nextLine().split(" ");
            AppInfo appInfo = new AppInfo(data[0], Integer.parseInt(data[1]), data[2], data[3]);
            apps.add(appInfo);
        }
        String timePoint = in.nextLine();
        int time = AppInfo.timeToInt(timePoint);
        List<AppInfo> registers = new ArrayList<>();
        for (AppInfo app : apps) {
            if (app.startTime >= app.endTime) {
                continue;
            }
            //如果App的优先级相同，则后添加的App不能注册
            if (registers.stream().anyMatch(a -> a.priority == app.priority)) {
                continue;
            }
            //如果高优先级的App时间和低优先级的时间段有冲突，则系统会自动注销低优先级的时段
            if (registers.stream().anyMatch(a -> a.startTime < app.endTime && app.startTime < a.endTime)) {
                if (registers.stream().anyMatch(a -> a.priority < app.priority)) {
                    registers.removeIf(/*a -> a.name.equals(app.name) &&*/ a -> a.startTime < app.endTime && app.startTime < a.endTime);
                    registers.add(app);
                }
            } else {
                registers.add(app);
            }
        }

        AppInfo found = registers.stream().filter(a -> a.startTime <= time && time < a.endTime).findFirst().orElse(null);
        if (found == null) {
            System.out.println("NA");
        } else {
            System.out.println(found.name);
        }
    }
}
