//public class Benchmark {
//    private static float testMoveGenerator(String board, int count) {
//        long sum = 0;
//        for(int i = 0; i < count; i++) {
//            Logic.Board b = new Logic.Board(board);
//            long start = System.nanoTime();
//            b.moveGenerator();
//            long elapsedTime = System.nanoTime() - start;
//            sum += elapsedTime;
//        }
//        return sum * (float) Math.pow(10,-6);
//    }
//
//    public static void Logic.main(String[] args) {
//        System.out.println("------------ 7x7 - Empty ------------");
//        System.out.println(testMoveGenerator("-------/-------/-------/-------/-------/-------/-------", 1) + " ms");
//        System.out.println(testMoveGenerator("-------/-------/-------/-------/-------/-------/-------", 10) + " ms");
//        System.out.println(testMoveGenerator("-------/-------/-------/-------/-------/-------/-------", 100) + " ms");
//        System.out.println(testMoveGenerator("-------/-------/-------/-------/-------/-------/-------", 1000) + " ms");
//        System.out.println(testMoveGenerator("-------/-------/-------/-------/-------/-------/-------", 10000) + " ms");
//        System.out.println(testMoveGenerator("-------/-------/-------/-------/-------/-------/-------", 100000) + " ms");
//        System.out.println("------------ 7x7 - Full ------------");
//        System.out.println(testMoveGenerator("-w--w--/wbw-bbw/b-b-b--/-w--w--/-b----w/wb-bw-b/-wb----", 1) + " ms");
//        System.out.println(testMoveGenerator("-w--w--/wbw-bbw/b-b-b--/-w--w--/-b----w/wb-bw-b/-wb----", 10) + " ms");
//        System.out.println(testMoveGenerator("-w--w--/wbw-bbw/b-b-b--/-w--w--/-b----w/wb-bw-b/-wb----", 100) + " ms");
//        System.out.println(testMoveGenerator("-w--w--/wbw-bbw/b-b-b--/-w--w--/-b----w/wb-bw-b/-wb----", 1000) + " ms");
//        System.out.println(testMoveGenerator("-w--w--/wbw-bbw/b-b-b--/-w--w--/-b----w/wb-bw-b/-wb----", 10000) + " ms");
//        System.out.println(testMoveGenerator("-w--w--/wbw-bbw/b-b-b--/-w--w--/-b----w/wb-bw-b/-wb----", 100000) + " ms");
//        System.out.println("------------ 9x9 - Empty ------------");
//        System.out.println(testMoveGenerator("---------/---------/---------/---------/---------/---------/---------/---------/---------", 1) + " ms");
//        System.out.println(testMoveGenerator("---------/---------/---------/---------/---------/---------/---------/---------/---------", 10) + " ms");
//        System.out.println(testMoveGenerator("---------/---------/---------/---------/---------/---------/---------/---------/---------", 100) + " ms");
//        System.out.println(testMoveGenerator("---------/---------/---------/---------/---------/---------/---------/---------/---------", 1000) + " ms");
//        System.out.println(testMoveGenerator("---------/---------/---------/---------/---------/---------/---------/---------/---------", 10000) + " ms");
//        System.out.println(testMoveGenerator("---------/---------/---------/---------/---------/---------/---------/---------/---------", 100000) + " ms");
//        System.out.println("------------ 9x9 - Full ------------");
//        System.out.println(testMoveGenerator("-w-w-b---/-b-b-w--w/--wb-wb-b/-w-w-w--w/-b-b-b--b/----w-w--/bw-bb-b--/---w-----/-wb--wb--", 1) + " ms");
//        System.out.println(testMoveGenerator("-w-w-b---/-b-b-w--w/--wb-wb-b/-w-w-w--w/-b-b-b--b/----w-w--/bw-bb-b--/---w-----/-wb--wb--", 10) + " ms");
//        System.out.println(testMoveGenerator("-w-w-b---/-b-b-w--w/--wb-wb-b/-w-w-w--w/-b-b-b--b/----w-w--/bw-bb-b--/---w-----/-wb--wb--", 100) + " ms");
//        System.out.println(testMoveGenerator("-w-w-b---/-b-b-w--w/--wb-wb-b/-w-w-w--w/-b-b-b--b/----w-w--/bw-bb-b--/---w-----/-wb--wb--", 1000) + " ms");
//        System.out.println(testMoveGenerator("-w-w-b---/-b-b-w--w/--wb-wb-b/-w-w-w--w/-b-b-b--b/----w-w--/bw-bb-b--/---w-----/-wb--wb--", 10000) + " ms");
//        System.out.println(testMoveGenerator("-w-w-b---/-b-b-w--w/--wb-wb-b/-w-w-w--w/-b-b-b--b/----w-w--/bw-bb-b--/---w-----/-wb--wb--", 100000) + " ms");
//        System.out.println("------------ 11x11 - Empty ------------");
//        System.out.println(testMoveGenerator("-----------/-----------/-----------/-----------/-----------/-----------/-----------/-----------/-----------/-----------/-----------", 1) + " ms");
//        System.out.println(testMoveGenerator("-----------/-----------/-----------/-----------/-----------/-----------/-----------/-----------/-----------/-----------/-----------", 10) + " ms");
//        System.out.println(testMoveGenerator("-----------/-----------/-----------/-----------/-----------/-----------/-----------/-----------/-----------/-----------/-----------", 100) + " ms");
//        System.out.println(testMoveGenerator("-----------/-----------/-----------/-----------/-----------/-----------/-----------/-----------/-----------/-----------/-----------", 1000) + " ms");
//        System.out.println(testMoveGenerator("-----------/-----------/-----------/-----------/-----------/-----------/-----------/-----------/-----------/-----------/-----------", 10000) + " ms");
//        System.out.println(testMoveGenerator("-----------/-----------/-----------/-----------/-----------/-----------/-----------/-----------/-----------/-----------/-----------", 100000) + " ms");
//        System.out.println("------------ 11x11 - Full ------------");
//        System.out.println(testMoveGenerator("wb-wb-wb-wb/bw-bw-bw-bw/ww-wwwwb---/bb-bbbwb---/b---bwbw-w-/w-bwbwwb-bb/w-w-wbbw-bw/b-b-bwwb-w-/b-w-wbbw---/w-b-bwwb-wb/----bwwb-bw", 1) + " ms");
//        System.out.println(testMoveGenerator("wb-wb-wb-wb/bw-bw-bw-bw/ww-wwwwb---/bb-bbbwb---/b---bwbw-w-/w-bwbwwb-bb/w-w-wbbw-bw/b-b-bwwb-w-/b-w-wbbw---/w-b-bwwb-wb/----bwwb-bw", 10) + " ms");
//        System.out.println(testMoveGenerator("wb-wb-wb-wb/bw-bw-bw-bw/ww-wwwwb---/bb-bbbwb---/b---bwbw-w-/w-bwbwwb-bb/w-w-wbbw-bw/b-b-bwwb-w-/b-w-wbbw---/w-b-bwwb-wb/----bwwb-bw", 100) + " ms");
//        System.out.println(testMoveGenerator("wb-wb-wb-wb/bw-bw-bw-bw/ww-wwwwb---/bb-bbbwb---/b---bwbw-w-/w-bwbwwb-bb/w-w-wbbw-bw/b-b-bwwb-w-/b-w-wbbw---/w-b-bwwb-wb/----bwwb-bw", 1000) + " ms");
//        System.out.println(testMoveGenerator("wb-wb-wb-wb/bw-bw-bw-bw/ww-wwwwb---/bb-bbbwb---/b---bwbw-w-/w-bwbwwb-bb/w-w-wbbw-bw/b-b-bwwb-w-/b-w-wbbw---/w-b-bwwb-wb/----bwwb-bw", 10000) + " ms");
//        System.out.println(testMoveGenerator("wb-wb-wb-wb/bw-bw-bw-bw/ww-wwwwb---/bb-bbbwb---/b---bwbw-w-/w-bwbwwb-bb/w-w-wbbw-bw/b-b-bwwb-w-/b-w-wbbw---/w-b-bwwb-wb/----bwwb-bw", 100000) + " ms");
//    }
//}
