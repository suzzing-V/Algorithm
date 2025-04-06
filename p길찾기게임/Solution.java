import java.util.*;

class Solution {

    private PriorityQueue<Node> poses = new PriorityQueue<>();
    private PriorityQueue<Node> q = new PriorityQueue<>();
    private int idx = 0;
    private int[][] answer;

    private class Node implements Comparable<Node> {
        private int num;
        private int x;
        private int y;
        private int minX;
        private int maxX;
        private Node left;
        private Node right;

        private Node(int num, int x, int y) {
            this.num = num;
            this.x = x;
            this.y = y;
            this.minX = 0;
            this.maxX = 100001;
            this.left = null;
            this.right = null;
        }

        public void changeMinX(int minX) {
            this.minX = minX;
        }

        public void changeMaxX(int maxX) {
            this.maxX = maxX;
        }

        @Override
        public int compareTo(Node n) {
            if(this.y == n.y) {
                return this.x - n.x;
            }
            return n.y - this.y;
        }
    }

    public int[][] solution(int[][] nodeinfo) {
        for(int i = 0; i < nodeinfo.length; i++) {
            int x = nodeinfo[i][0];
            int y = nodeinfo[i][1];
            poses.add(new Node(i + 1, x, y));
        }

        Node head = poses.remove();
        q.add(head);
        while(!q.isEmpty()) {
            Node curr = q.remove();
            // System.out.println(curr.x + " " + curr.y);
            int ny = -1;
            while(!poses.isEmpty()) {
                Node next = poses.peek();
                if(ny != -1 && ny != next.y) break; // 다음 층의 노드면 멈춤
                if(ny == -1) { // 아직 지정 안됐으면 현재 노드의 y좌표 저장한다. 이는 해당 노드가 현재 층에 존재하는지 확인하기 위한 플래그.
                    ny = next.y;
                }

                if(next.x >= curr.minX && next.x < curr.x) { // 다음 노드가 현재 노드의 왼쪽에 있으면
                    curr.left = next;
                    next.changeMinX(curr.minX);
                    next.changeMaxX(curr.x - 1); // 지금까지 거쳐온 노드의 범위를 지켜야한다.
                    // System.out.println("왼: " + next.x + " " + next.y);
                    q.add(next);
                    poses.remove(next);
                } else if(next.x > curr.x && next.x <= curr.maxX) { // 오른쪽에 있음
                    curr.right = next;
                    next.changeMinX(curr.x + 1);
                    next.changeMaxX(curr.maxX);
                    // System.out.println("오: " + next.x + " " + next.y);
                    q.add(next);
                    poses.remove(next);
                } else { // 현재 노드의 범위에 없으면 멈춘다.
                    break;
                }
            }
        }

        // 전위순회
        answer = new int[2][nodeinfo.length];
        middleFirst(head);
        //후위순회
        idx = 0;
        middleLast(head);
        return answer;
    }

    private void middleFirst(Node head) {
        answer[0][idx++] = head.num;
        if(head.left == null && head.right == null) {
            return;
        }

        if(head.left != null) middleFirst(head.left);
        if(head.right != null) middleFirst(head.right);
    }

    private void middleLast(Node head) {
        if(head.left == null && head.right == null) {
            answer[1][idx++] = head.num;
            return;
        }

        if(head.left != null) middleLast(head.left);
        if(head.right != null) middleLast(head.right);
        answer[1][idx++] = head.num;
    }
}
