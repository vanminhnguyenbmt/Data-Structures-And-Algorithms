package com.nguyenvm.array.reveal_cards_in_increasing_order;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class RevealCards {
    /**
     * Dry Run
     * Original deck: [17, 13, 11, 2, 3, 5, 7]
     * After sorting: [17, 13, 11, 7, 5, 3, 2]
     *
     * Initialize deque with the largest card:
     * Deque: [17]
     *
     * Card 13: First, move 17 to the front, then insert 13 at the front: [13, 17]
     * Card 11: First, move 17 to the front, then insert 11 at the front: [11, 17, 13]
     * Card 7: First, move 13 to the front, then insert 7 at the front: [7, 13, 11, 17]
     * Card 5: First, move 17 to the front, then insert 5 at the front: [5, 17, 7, 13, 11]
     * Card 3: First, move 11 to the front, then insert 3 at the front: [3, 11,5, 17, 7, 13]
     * Card 2: First, move 17 to the front, then insert 13 at the front: [2, 13, 3, 11, 5, 17, 7]
     */
    public static int[] deckRevealedIncreasing(int[] deck) {
        Arrays.sort(deck);
        Deque<Integer> deque = new ArrayDeque<>();
        deque.offer(deck[deck.length - 1]);
        for (int i = deck.length - 2; i >= 0; i--) {
            deque.offerFirst(deque.pollLast());
            deque.offerFirst(deck[i]);
        }

        int[] deckOrdering = new int[deck.length];
        int index = 0;
        while (!deque.isEmpty()) {
            deckOrdering[index++] = deque.pollFirst();
        }
        return deckOrdering;
    }

    public static void main(String[] args) {
        int[] deck = {17, 13, 11, 2, 3, 5, 7};
        System.out.println(Arrays.toString(deckRevealedIncreasing(deck)));
    }
}
