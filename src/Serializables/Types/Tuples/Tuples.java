package Serializables.Types.Tuples;

import Serializables.Types.ProtocolType;

import java.nio.ByteBuffer;
import java.util.function.Function;

public class Tuples implements ProtocolType{
    public static class Tuple2<A, B> extends AbstractTuple {
        public Tuple2(A a, B b) {
            super(a, b);
        }

        public static <A, B> Tuple2<A, B> readFrom(ByteBuffer buffer, Function<ByteBuffer, A> dsrl1, Function<ByteBuffer, B> dsrl2) {
            return (Tuple2<A, B>) AbstractTuple.readFrom(buffer, new Function[]{dsrl1, dsrl2});
        }
    }

    public static class Tuple3<A, B, C> extends AbstractTuple {
        public Tuple3(A a, B b, C c) {
            super(a, b, c);
        }

        public static <A, B, C> Tuple3<A, B, C> readFrom(ByteBuffer buffer, Function<ByteBuffer, A> dsrl1, Function<ByteBuffer, B> dsrl2, Function<ByteBuffer, C> dsrl3) {
            return (Tuple3<A, B, C>) AbstractTuple.readFrom(buffer, new Function[]{dsrl1, dsrl2, dsrl3});
        }
    }

    public static class Tuple4<A, B, C, D> extends AbstractTuple {
        public Tuple4(A a, B b, C c, D d) {
            super(a, b, c, d);
        }

        public static <A, B, C, D> Tuple4<A, B, C, D> readFrom(ByteBuffer buffer, Function<ByteBuffer, A> dsrl1, Function<ByteBuffer, B> dsrl2, Function<ByteBuffer, C> dsrl3, Function<ByteBuffer, D> dsrl4) {
            return (Tuple4<A, B, C, D>) AbstractTuple.readFrom(buffer, new Function[]{dsrl1, dsrl2, dsrl3, dsrl4});
        }
    }

    public static class Tuple5<A, B, C, D, E> extends AbstractTuple {
        public Tuple5(A a, B b, C c, D d, E e) {
            super(a, b, c, d, e);
        }

        public static <A, B, C, D, E> Tuple5<A, B, C, D, E> readFrom(ByteBuffer buffer, Function<ByteBuffer, A> dsrl1, Function<ByteBuffer, B> dsrl2, Function<ByteBuffer, C> dsrl3, Function<ByteBuffer, D> dsrl4, Function<ByteBuffer, E> dsrl5) {
            return (Tuple5<A, B, C, D, E>) AbstractTuple.readFrom(buffer, new Function[]{dsrl1, dsrl2, dsrl3, dsrl4, dsrl5});
        }
    }
    public static class Tuple6<A, B, C, D, E, F> extends AbstractTuple {
        public Tuple6(A a, B b, C c, D d, E e, F f) {
            super(a, b, c, d, e, f);
        }

        public static <A, B, C, D, E, F> Tuple6<A, B, C, D, E, F> readFrom(ByteBuffer buffer, Function<ByteBuffer, A> dsrl1, Function<ByteBuffer, B> dsrl2, Function<ByteBuffer, C> dsrl3, Function<ByteBuffer, D> dsrl4, Function<ByteBuffer, E> dsrl5, Function<ByteBuffer, F> dsrl6) {
            return (Tuple6<A, B, C, D, E, F>) AbstractTuple.readFrom(buffer, new Function[]{dsrl1, dsrl2, dsrl3, dsrl4, dsrl5, dsrl6});
        }
    }

    public static class Tuple7<A, B, C, D, E, F, G> extends AbstractTuple {
        public Tuple7(A a, B b, C c, D d, E e, F f, G g) {
            super(a, b, c, d, e, f, g);
        }

        public static <A, B, C, D, E, F, G> Tuple7<A, B, C, D, E, F, G> readFrom(ByteBuffer buffer, Function<ByteBuffer, A> dsrl1, Function<ByteBuffer, B> dsrl2, Function<ByteBuffer, C> dsrl3, Function<ByteBuffer, D> dsrl4, Function<ByteBuffer, E> dsrl5, Function<ByteBuffer, F> dsrl6, Function<ByteBuffer, G> dsrl7) {
            return (Tuple7<A, B, C, D, E, F, G>) AbstractTuple.readFrom(buffer, new Function[]{dsrl1, dsrl2, dsrl3, dsrl4, dsrl5, dsrl6, dsrl7});
        }
    }

    public static class Tuple8<A, B, C, D, E, F, G, H> extends AbstractTuple {
        public Tuple8(A a, B b, C c, D d, E e, F f, G g, H h) {
            super(a, b, c, d, e, f, g, h);
        }

        public static <A, B, C, D, E, F, G, H> Tuple8<A, B, C, D, E, F, G, H> readFrom(ByteBuffer buffer, Function<ByteBuffer, A> dsrl1, Function<ByteBuffer, B> dsrl2, Function<ByteBuffer, C> dsrl3, Function<ByteBuffer, D> dsrl4, Function<ByteBuffer, E> dsrl5, Function<ByteBuffer, F> dsrl6, Function<ByteBuffer, G> dsrl7, Function<ByteBuffer, H> dsrl8) {
            return (Tuple8<A, B, C, D, E, F, G, H>) AbstractTuple.readFrom(buffer, new Function[]{dsrl1, dsrl2, dsrl3, dsrl4, dsrl5, dsrl6, dsrl7, dsrl8});
        }
    }

    public static class Tuple9<A, B, C, D, E, F, G, H, I> extends AbstractTuple {
        public Tuple9(A a, B b, C c, D d, E e, F f, G g, H h, I i) {
            super(a, b, c, d, e, f, g, h, i);
        }

        public static <A, B, C, D, E, F, G, H, I> Tuple9<A, B, C, D, E, F, G, H, I> readFrom(ByteBuffer buffer, Function<ByteBuffer, A> dsrl1, Function<ByteBuffer, B> dsrl2, Function<ByteBuffer, C> dsrl3, Function<ByteBuffer, D> dsrl4, Function<ByteBuffer, E> dsrl5, Function<ByteBuffer, F> dsrl6, Function<ByteBuffer, G> dsrl7, Function<ByteBuffer, H> dsrl8, Function<ByteBuffer, I> dsrl9) {
            return (Tuple9<A, B, C, D, E, F, G, H, I>) AbstractTuple.readFrom(buffer, new Function[]{dsrl1, dsrl2, dsrl3, dsrl4, dsrl5, dsrl6, dsrl7, dsrl8, dsrl9});
        }
    }

    public static class Tuple10<A, B, C, D, E, F, G, H, I, J> extends AbstractTuple {
        public Tuple10(A a, B b, C c, D d, E e, F f, G g, H h, I i, J j) {
            super(a, b, c, d, e, f, g, h, i, j);
        }

        public static <A, B, C, D, E, F, G, H, I, J> Tuple10<A, B, C, D, E, F, G, H, I, J> readFrom(ByteBuffer buffer, Function<ByteBuffer, A> dsrl1, Function<ByteBuffer, B> dsrl2, Function<ByteBuffer, C> dsrl3, Function<ByteBuffer, D> dsrl4, Function<ByteBuffer, E> dsrl5, Function<ByteBuffer, F> dsrl6, Function<ByteBuffer, G> dsrl7, Function<ByteBuffer, H> dsrl8, Function<ByteBuffer, I> dsrl9, Function<ByteBuffer, J> dsrl10) {
            return (Tuple10<A, B, C, D, E, F, G, H, I, J>) AbstractTuple.readFrom(buffer, new Function[]{dsrl1, dsrl2, dsrl3, dsrl4, dsrl5, dsrl6, dsrl7, dsrl8, dsrl9, dsrl10});
        }
    }
    public static class Tuple11<A, B, C, D, E, F, G, H, I, J, K> extends AbstractTuple {
        public Tuple11(A a, B b, C c, D d, E e, F f, G g, H h, I i, J j, K k) {
            super(a, b, c, d, e, f, g, h, i, j, k);
        }

        public static <A, B, C, D, E, F, G, H, I, J, K> Tuple11<A, B, C, D, E, F, G, H, I, J, K> readFrom(ByteBuffer buffer, Function<ByteBuffer, A> dsrl1, Function<ByteBuffer, B> dsrl2, Function<ByteBuffer, C> dsrl3, Function<ByteBuffer, D> dsrl4, Function<ByteBuffer, E> dsrl5, Function<ByteBuffer, F> dsrl6, Function<ByteBuffer, G> dsrl7, Function<ByteBuffer, H> dsrl8, Function<ByteBuffer, I> dsrl9, Function<ByteBuffer, J> dsrl10, Function<ByteBuffer, K> dsrl11) {
            return (Tuple11<A, B, C, D, E, F, G, H, I, J, K>) AbstractTuple.readFrom(buffer, new Function[]{dsrl1, dsrl2, dsrl3, dsrl4, dsrl5, dsrl6, dsrl7, dsrl8, dsrl9, dsrl10, dsrl11});
        }
    }

    public static class Tuple12<A, B, C, D, E, F, G, H, I, J, K, L> extends AbstractTuple {
        public Tuple12(A a, B b, C c, D d, E e, F f, G g, H h, I i, J j, K k, L l) {
            super(a, b, c, d, e, f, g, h, i, j, k, l);
        }

        public static <A, B, C, D, E, F, G, H, I, J, K, L> Tuple12<A, B, C, D, E, F, G, H, I, J, K, L> readFrom(ByteBuffer buffer, Function<ByteBuffer, A> dsrl1, Function<ByteBuffer, B> dsrl2, Function<ByteBuffer, C> dsrl3, Function<ByteBuffer, D> dsrl4, Function<ByteBuffer, E> dsrl5, Function<ByteBuffer, F> dsrl6, Function<ByteBuffer, G> dsrl7, Function<ByteBuffer, H> dsrl8, Function<ByteBuffer, I> dsrl9, Function<ByteBuffer, J> dsrl10, Function<ByteBuffer, K> dsrl11, Function<ByteBuffer, L> dsrl12) {
            return (Tuple12<A, B, C, D, E, F, G, H, I, J, K, L>) AbstractTuple.readFrom(buffer, new Function[]{dsrl1, dsrl2, dsrl3, dsrl4, dsrl5, dsrl6, dsrl7, dsrl8, dsrl9, dsrl10, dsrl11, dsrl12});
        }
    }

    public static class Tuple13<A, B, C, D, E, F, G, H, I, J, K, L, M> extends AbstractTuple {
        public Tuple13(A a, B b, C c, D d, E e, F f, G g, H h, I i, J j, K k, L l, M m) {
            super(a, b, c, d, e, f, g, h, i, j, k, l, m);
        }

        public static <A, B, C, D, E, F, G, H, I, J, K, L, M> Tuple13<A, B, C, D, E, F, G, H, I, J, K, L, M> readFrom(ByteBuffer buffer, Function<ByteBuffer, A> dsrl1, Function<ByteBuffer, B> dsrl2, Function<ByteBuffer, C> dsrl3, Function<ByteBuffer, D> dsrl4, Function<ByteBuffer, E> dsrl5, Function<ByteBuffer, F> dsrl6, Function<ByteBuffer, G> dsrl7, Function<ByteBuffer, H> dsrl8, Function<ByteBuffer, I> dsrl9, Function<ByteBuffer, J> dsrl10, Function<ByteBuffer, K> dsrl11, Function<ByteBuffer, L> dsrl12, Function<ByteBuffer, M> dsrl13) {
            return (Tuple13<A, B, C, D, E, F, G, H, I, J, K, L, M>) AbstractTuple.readFrom(buffer, new Function[]{dsrl1, dsrl2, dsrl3, dsrl4, dsrl5, dsrl6, dsrl7, dsrl8, dsrl9, dsrl10, dsrl11, dsrl12, dsrl13});
        }
    }

    public static class Tuple14<A, B, C, D, E, F, G, H, I, J, K, L, M, N> extends AbstractTuple {
        public Tuple14(A a, B b, C c, D d, E e, F f, G g, H h, I i, J j, K k, L l, M m, N n) {
            super(a, b, c, d, e, f, g, h, i, j, k, l, m, n);
        }

        public static <A, B, C, D, E, F, G, H, I, J, K, L, M, N> Tuple14<A, B, C, D, E, F, G, H, I, J, K, L, M, N> readFrom(ByteBuffer buffer, Function<ByteBuffer, A> dsrl1, Function<ByteBuffer, B> dsrl2, Function<ByteBuffer, C> dsrl3, Function<ByteBuffer, D> dsrl4, Function<ByteBuffer, E> dsrl5, Function<ByteBuffer, F> dsrl6, Function<ByteBuffer, G> dsrl7, Function<ByteBuffer, H> dsrl8, Function<ByteBuffer, I> dsrl9, Function<ByteBuffer, J> dsrl10, Function<ByteBuffer, K> dsrl11, Function<ByteBuffer, L> dsrl12, Function<ByteBuffer, M> dsrl13, Function<ByteBuffer, N> dsrl14) {
            return (Tuple14<A, B, C, D, E, F, G, H, I, J, K, L, M, N>) AbstractTuple.readFrom(buffer, new Function[]{dsrl1, dsrl2, dsrl3, dsrl4, dsrl5, dsrl6, dsrl7, dsrl8, dsrl9, dsrl10, dsrl11, dsrl12, dsrl13, dsrl14});
        }
    }

    public static class Tuple15<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O> extends AbstractTuple {
        public Tuple15(A a, B b, C c, D d, E e, F f, G g, H h, I i, J j, K k, L l, M m, N n, O o) {
            super(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o);
        }

        public static <A, B, C, D, E, F, G, H, I, J, K, L, M, N, O> Tuple15<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O> readFrom(ByteBuffer buffer, Function<ByteBuffer, A> dsrl1, Function<ByteBuffer, B> dsrl2, Function<ByteBuffer, C> dsrl3, Function<ByteBuffer, D> dsrl4, Function<ByteBuffer, E> dsrl5, Function<ByteBuffer, F> dsrl6, Function<ByteBuffer, G> dsrl7, Function<ByteBuffer, H> dsrl8, Function<ByteBuffer, I> dsrl9, Function<ByteBuffer, J> dsrl10, Function<ByteBuffer, K> dsrl11, Function<ByteBuffer, L> dsrl12, Function<ByteBuffer, M> dsrl13, Function<ByteBuffer, N> dsrl14, Function<ByteBuffer, O> dsrl15) {
            return (Tuple15<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O>) AbstractTuple.readFrom(buffer, new Function[]{dsrl1, dsrl2, dsrl3, dsrl4, dsrl5, dsrl6, dsrl7, dsrl8, dsrl9, dsrl10, dsrl11, dsrl12, dsrl13, dsrl14, dsrl15});
        }
    }

    public static class Tuple16<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P> extends AbstractTuple {
        public Tuple16(A a, B b, C c, D d, E e, F f, G g, H h, I i, J j, K k, L l, M m, N n, O o, P p) {
            super(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p);
        }

        public static <A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P> Tuple16<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P> readFrom(ByteBuffer buffer, Function<ByteBuffer, A> dsrl1, Function<ByteBuffer, B> dsrl2, Function<ByteBuffer, C> dsrl3, Function<ByteBuffer, D> dsrl4, Function<ByteBuffer, E> dsrl5, Function<ByteBuffer, F> dsrl6, Function<ByteBuffer, G> dsrl7, Function<ByteBuffer, H> dsrl8, Function<ByteBuffer, I> dsrl9, Function<ByteBuffer, J> dsrl10, Function<ByteBuffer, K> dsrl11, Function<ByteBuffer, L> dsrl12, Function<ByteBuffer, M> dsrl13, Function<ByteBuffer, N> dsrl14, Function<ByteBuffer, O> dsrl15, Function<ByteBuffer, P> dsrl16) {
            return (Tuple16<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P>) AbstractTuple.readFrom(buffer, new Function[]{dsrl1, dsrl2, dsrl3, dsrl4, dsrl5, dsrl6, dsrl7, dsrl8, dsrl9, dsrl10, dsrl11, dsrl12, dsrl13, dsrl14, dsrl15, dsrl16});
        }
    }

    public static class Tuple17<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q> extends AbstractTuple {
        public Tuple17(A a, B b, C c, D d, E e, F f, G g, H h, I i, J j, K k, L l, M m, N n, O o, P p, Q q) {
            super(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q);
        }

        public static <A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q> Tuple17<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q> readFrom(ByteBuffer buffer, Function<ByteBuffer, A> dsrl1, Function<ByteBuffer, B> dsrl2, Function<ByteBuffer, C> dsrl3, Function<ByteBuffer, D> dsrl4, Function<ByteBuffer, E> dsrl5, Function<ByteBuffer, F> dsrl6, Function<ByteBuffer, G> dsrl7, Function<ByteBuffer, H> dsrl8, Function<ByteBuffer, I> dsrl9, Function<ByteBuffer, J> dsrl10, Function<ByteBuffer, K> dsrl11, Function<ByteBuffer, L> dsrl12, Function<ByteBuffer, M> dsrl13, Function<ByteBuffer, N> dsrl14, Function<ByteBuffer, O> dsrl15, Function<ByteBuffer, P> dsrl16, Function<ByteBuffer, Q> dsrl17) {
            return (Tuple17<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q>) AbstractTuple.readFrom(buffer, new Function[]{dsrl1, dsrl2, dsrl3, dsrl4, dsrl5, dsrl6, dsrl7, dsrl8, dsrl9, dsrl10, dsrl11, dsrl12, dsrl13, dsrl14, dsrl15, dsrl16, dsrl17});
        }
    }

    public static class Tuple18<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R> extends AbstractTuple {
        public Tuple18(A a, B b, C c, D d, E e, F f, G g, H h, I i, J j, K k, L l, M m, N n, O o, P p, Q q, R r) {
            super(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r);
        }

        public static <A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R> Tuple18<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R> readFrom(ByteBuffer buffer, Function<ByteBuffer, A> dsrl1, Function<ByteBuffer, B> dsrl2, Function<ByteBuffer, C> dsrl3, Function<ByteBuffer, D> dsrl4, Function<ByteBuffer, E> dsrl5, Function<ByteBuffer, F> dsrl6, Function<ByteBuffer, G> dsrl7, Function<ByteBuffer, H> dsrl8, Function<ByteBuffer, I> dsrl9, Function<ByteBuffer, J> dsrl10, Function<ByteBuffer, K> dsrl11, Function<ByteBuffer, L> dsrl12, Function<ByteBuffer, M> dsrl13, Function<ByteBuffer, N> dsrl14, Function<ByteBuffer, O> dsrl15, Function<ByteBuffer, P> dsrl16, Function<ByteBuffer, Q> dsrl17, Function<ByteBuffer, R> dsrl18) {
            return (Tuple18<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R>) AbstractTuple.readFrom(buffer, new Function[]{dsrl1, dsrl2, dsrl3, dsrl4, dsrl5, dsrl6, dsrl7, dsrl8, dsrl9, dsrl10, dsrl11, dsrl12, dsrl13, dsrl14, dsrl15, dsrl16, dsrl17, dsrl18});
        }
    }

    public static class Tuple19<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S> extends AbstractTuple {
        public Tuple19(A a, B b, C c, D d, E e, F f, G g, H h, I i, J j, K k, L l, M m, N n, O o, P p, Q q, R r, S s) {
            super(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s);
        }

        public static <A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S> Tuple19<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S> readFrom(ByteBuffer buffer, Function<ByteBuffer, A> dsrl1, Function<ByteBuffer, B> dsrl2, Function<ByteBuffer, C> dsrl3, Function<ByteBuffer, D> dsrl4, Function<ByteBuffer, E> dsrl5, Function<ByteBuffer, F> dsrl6, Function<ByteBuffer, G> dsrl7, Function<ByteBuffer, H> dsrl8, Function<ByteBuffer, I> dsrl9, Function<ByteBuffer, J> dsrl10, Function<ByteBuffer, K> dsrl11, Function<ByteBuffer, L> dsrl12, Function<ByteBuffer, M> dsrl13, Function<ByteBuffer, N> dsrl14, Function<ByteBuffer, O> dsrl15, Function<ByteBuffer, P> dsrl16, Function<ByteBuffer, Q> dsrl17, Function<ByteBuffer, R> dsrl18, Function<ByteBuffer, S> dsrl19) {
            return (Tuple19<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S>) AbstractTuple.readFrom(buffer, new Function[]{dsrl1, dsrl2, dsrl3, dsrl4, dsrl5, dsrl6, dsrl7, dsrl8, dsrl9, dsrl10, dsrl11, dsrl12, dsrl13, dsrl14, dsrl15, dsrl16, dsrl17, dsrl18, dsrl19});
        }
    }

    public static class Tuple20<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T> extends AbstractTuple {
        public Tuple20(A a, B b, C c, D d, E e, F f, G g, H h, I i, J j, K k, L l, M m, N n, O o, P p, Q q, R r, S s, T t) {
            super(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t);
        }

        public static <A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T> Tuple20<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T> readFrom(ByteBuffer buffer, Function<ByteBuffer, A> dsrl1, Function<ByteBuffer, B> dsrl2, Function<ByteBuffer, C> dsrl3, Function<ByteBuffer, D> dsrl4, Function<ByteBuffer, E> dsrl5, Function<ByteBuffer, F> dsrl6, Function<ByteBuffer, G> dsrl7, Function<ByteBuffer, H> dsrl8, Function<ByteBuffer, I> dsrl9, Function<ByteBuffer, J> dsrl10, Function<ByteBuffer, K> dsrl11, Function<ByteBuffer, L> dsrl12, Function<ByteBuffer, M> dsrl13, Function<ByteBuffer, N> dsrl14, Function<ByteBuffer, O> dsrl15, Function<ByteBuffer, P> dsrl16, Function<ByteBuffer, Q> dsrl17, Function<ByteBuffer, R> dsrl18, Function<ByteBuffer, S> dsrl19, Function<ByteBuffer, T> dsrl20) {
            return (Tuple20<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T>) AbstractTuple.readFrom(buffer, new Function[]{dsrl1, dsrl2, dsrl3, dsrl4, dsrl5, dsrl6, dsrl7, dsrl8, dsrl9, dsrl10, dsrl11, dsrl12, dsrl13, dsrl14, dsrl15, dsrl16, dsrl17, dsrl18, dsrl19, dsrl20});
        }
    }
}
