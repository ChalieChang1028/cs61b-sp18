public class LinkedListDeque <T> {

    private ListNode sentinel;
    private int size;

    private class ListNode {
        public T item;
        public ListNode pre;
        public ListNode next;


        public ListNode(T i, ListNode p, ListNode n) {
            item = i;
            pre = p;
            next = n;
        }
    }

    public LinkedListDeque(){
        size = 0;
        sentinel = new ListNode(null,null,null);
        sentinel.next = sentinel;
        sentinel.pre = sentinel;
    }

    /*public LinkedListDeque(T item){

    }*/

    public int size(){
        return size;
    }

    public void addFirst(T item){
        sentinel.next = new ListNode(item,sentinel,sentinel.next);
        sentinel.next.next.pre = sentinel.next;//forget this see else code
        size = size + 1;
    }

    public void addLast(T item){
        sentinel.pre = new ListNode(item,sentinel.pre,sentinel);
        sentinel.pre.pre.next = sentinel.pre;
        size = size + 1;
    }

    public boolean isEmpty(){
        //boolean ans=(size==0)?true:false;//next line code is more elegant
        return (size == 0);
    }

    public void printDeque(){

        if(size != 0){
            ListNode temp = sentinel.next;
            for(int i = 0;i < size;i++) {
                System.out.print(temp.item);
                System.out.print(' ');
                temp = temp.next;
            }
        }
        System.out.println();
    }

    public T removeFirst(){
        if(size!=0) {
            ListNode temp = sentinel.next;
            T target = temp.item;
            sentinel.next.next.pre = sentinel;
            sentinel.next = sentinel.next.next;
            temp = null;
            size = size - 1;
            return target;
        }
        return null;
    }

    public T removeLast(){
        if(size!=0) {
            ListNode temp = sentinel.pre;
            T target = temp.item;
            sentinel.pre.pre.next = sentinel;
            sentinel.pre = sentinel.pre.pre;
            temp = null;
            size = size -1;
            return target;
        }
        return null;
    }

    public T get(int index){
        if(index >= size){
            return null;
        }
        ListNode temp = sentinel.next;
        for(int i = 0; i < size; i++){
            temp = temp.next;
        }
        return temp.item;
    }


    private T getRecursive(int index , ListNode move){
        if(index == 0){
            return move.item;
        }
        return getRecursive(index-1,move.next);
    }

    public T getRecursive(int index){
        if(index >= size){
            return null;
        }
        return getRecursive(index , sentinel.next);
    }









}
