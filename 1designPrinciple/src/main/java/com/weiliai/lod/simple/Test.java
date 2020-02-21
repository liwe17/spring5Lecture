package com.weiliai.lod.simple;

/**
 * @Author: Doug Li
 * @Date 2020/2/21
 * @Describe: 根据迪米特法则,boss无需知道course,只需要和teamLeader有关联
 */
public class Test {

    public static void main(String[] args) {
        Boss boss = new Boss();
        TeamLeader teamLeader = new TeamLeader();
        boss.commandCheckNumber(teamLeader);
    }
}
