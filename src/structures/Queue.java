package structures;

import java.util.NoSuchElementException;

public class Queue<T> implements UnboundedQueueInterface<T> {
	
	LLNode<T> head = null;
	LLNode<T> tail = null;
	int size = 0;
	
	public Queue() {
		head = null;
		tail = null;
		size = 0;
	}
	
	public Queue(Queue<T> other) {
		head = other.head;
		size = other.size;
		/*
		if(other.size == 0)
			return;
		head = new LLNode<T>(other.dequeue());
		for (int i = 1; i < other.size; i++){
			enqueue(other.dequeue());
		}
		size = other.size;
		// TODO 2
		*/
	}
	
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void enqueue(T element) {
		if (head == null){
			head = new LLNode<T>(element);
			tail = head;
			size++;
			return;
		}
		tail.setNext(new LLNode<T>(element));
		tail = tail.getNext();
		
		size++;
	}

	@Override
	public T dequeue() throws NoSuchElementException {
		size--;
		if (head == null)
			throw new NoSuchElementException();
		
		LLNode<T> temp = head;
		head = head.getNext();
		return temp.getData();
	}

	@Override
	public T peek() throws NoSuchElementException {
		if (head == null)
			throw new NoSuchElementException();
		
		return head.getData();
	}

	@Override
	public UnboundedQueueInterface<T> reversed() {
		if (head == null)
			return null;
		
		LLNode<T> stackHead = new LLNode<T>(head.getData());
		LLNode<T> temp = head;
		temp = temp.getNext();
		
		for (int i = 1; i < size; i++){
			LLNode<T> t = new LLNode<T>(temp.getData());
			t.setNext(temp);
			temp = t;
			}
		
		UnboundedQueueInterface<T> q = new Queue<T>();
		
		for (int i = 0; i < size; i++){
			q.enqueue(temp.getData());
			temp = temp.getNext();
		}
		
		return q;
	}
}
