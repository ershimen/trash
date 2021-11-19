import stack.SimpleStack;

public class TestSimpleStack{
    public static void main(String[] args) {
        SimpleStack<Integer> queue1=new SimpleStack<Integer>();
        assert queue1.size()==0;
        assert queue1.peek()==null;
        assert queue1.poll()==null;
        assert queue1.toString()=="";

        queue1.push(1);
        assert queue1.size()==1;
        assert queue1.peek()==1;
        assert "1".equals(queue1.toString());
        queue1.push(2);
        queue1.push(3);

        assert queue1.size()==3;
        assert queue1.peek()==3;
        queue1.poll();
        assert queue1.size()==2;
        assert queue1.peek()==2;

        queue1.clear();
        assert queue1.size()==0;
        assert queue1.peek()==null;
        assert queue1.poll()==null;
        assert queue1.toString()=="";

        SimpleStack<String> queue2=new SimpleStack<String>();
        assert queue2.size()==0;
        assert queue2.peek()==null;
        assert queue2.poll()==null;
        assert queue2.toString()=="";

        queue2.push("igeaigb");
        assert queue2.size()==1;
        assert queue2.peek()=="igeaigb";
        assert queue2.poll()=="igeaigb";
        assert queue2.size()==0;

        queue2.clear();
        assert queue2.size()==0;

        final int N=10;
        String aux;
        for(int i=0; i<N ; i++){
            aux="dato-"+i;
            queue2.push(aux);
        }
        assert queue2.size()==10;
        assert "dato-9".equals(queue2.peek());
        assert "dato-9".equals(queue2.poll());
        assert queue2.size()==9;

        assert "dato-8, dato-7, dato-6, dato-5, dato-4, dato-3, dato-2, dato-1, dato-0".equals(queue2.toString());
        for(int i=0; i<7; i++)
            queue2.poll();
        assert queue2.size()==2;
    }
}
