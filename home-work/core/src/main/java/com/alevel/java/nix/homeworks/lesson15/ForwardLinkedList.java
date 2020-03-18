package com.alevel.java.nix.homeworks.lesson15;

import java.util.AbstractList;

public class ForwardLinkedList<E> extends AbstractList<E> {

    private ForwardLinkedList.Node<E> head;

    private int size;

    ForwardLinkedList() {
        this.size = 0;
    }

    private static class Node<E> {
        E value;
        Node<E> next;

        public Node(E element) {
            value = element;
        }
    }

    @Override
    public E get(int i) {
        if (head == null) {
            throw new IndexOutOfBoundsException(i);
        } else if (i >= size) {
            throw new IndexOutOfBoundsException(i);
        } else if (i < 0) {
            throw new IndexOutOfBoundsException(i);
        }
        ForwardLinkedList.Node<E> tempNode = head;
        for (int index = 0; index < i; index++) {
            tempNode = tempNode.next;
        }
        return tempNode.value;
    }

    @Override
    public int size() {
        return this.size;
    }

    public void addFirst(E e) {
        if(size == Integer.MAX_VALUE - 1) {
            return;
        }
        if (e == null) {
            throw new NullPointerException("Element is null!");
        }
        ForwardLinkedList.Node<E> newHead = new ForwardLinkedList.Node<>(e);
        newHead.next = head;
        head = newHead;
        size++;
    }

    public void addLast(E e) {
        if(size == Integer.MAX_VALUE - 1) {
            return;
        }
        if (e == null) {
            throw new NullPointerException("Element is null!");
        }
        if (head == null) {
            head = new ForwardLinkedList.Node<>(e);
            size++;
            return;
        }
        ForwardLinkedList.Node<E> tempNode = head;
        while (tempNode.next != null) {
            tempNode = tempNode.next;
        }
        tempNode.next = new ForwardLinkedList.Node<>(e);
        size++;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    public E removeFirst() {
        if (size == 0) {
            throw new NullPointerException("There is no elements in list!");
        }
        ForwardLinkedList.Node<E> tempNode = head;
        head = head.next;
        size--;
        return tempNode.value;
    }

    public E removeLast() {
        if (size == 0) {
            throw new NullPointerException("There is no elements in list!");
        }
        ForwardLinkedList.Node<E> tempNode = head;
        ForwardLinkedList.Node<E> prev = tempNode;
        while (tempNode.next != null) {
            prev = tempNode;
            tempNode = tempNode.next;
        }
        prev.next = null;
        size--;
        return tempNode.value;
    }

    @Override
    public boolean add(E e) {
        if(size == Integer.MAX_VALUE - 1) {
            return false;
        }
        addLast(e);
        return true;
    }
}
