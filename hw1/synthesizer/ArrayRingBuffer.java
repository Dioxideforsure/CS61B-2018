package synthesizer;

import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Array for storing the buffer data. */
    private final T[] ringBuffer;
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        ringBuffer = (T[]) new Object[capacity];
        first = last = 0;
        this.capacity = capacity;
        fillCount = 0;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    @Override
    public void enqueue(T item) {
        if (isFull()) {
            throw new RuntimeException("Ring Buffer Overflow");
        }
        ringBuffer[last++] = item;
        if (last == capacity) {
            last = 0;
        }
        fillCount++;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow");
        }
        T item = ringBuffer[first++];
        if (first == capacity) {
            first = 0;
        }
        fillCount--;
        return item;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow");
        }
        return ringBuffer[first];
    }

    /*
     * Create an iterator making this iterable
     */

    @Override
    public Iterator<T> iterator() {
        return new BufferIterator();
    }

    private class BufferIterator implements Iterator<T> {
        private int bufferWiz = 0;
        private int Pos = first;

        @Override
        public boolean hasNext() {
            return bufferWiz == capacity - 1;
        }

        @Override
        public T next() {
            T item = ringBuffer[Pos++];
            if (Pos == capacity) {
                Pos = 0;
            }
            bufferWiz++;
            return item;
        }
    }
}
