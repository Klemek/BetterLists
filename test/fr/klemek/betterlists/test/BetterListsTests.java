package fr.klemek.betterlists.test;

import fr.klemek.betterlists.BetterArrayList;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class BetterListsTests {

    protected class Dummy {
        final double d;
        final String s;

        public Dummy(double d, String s) {
            this.d = d;
            this.s = s;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Dummy other = (Dummy) obj;
            if (!getOuterType().equals(other.getOuterType()))
                return false;
            if (Double.doubleToLongBits(d) != Double.doubleToLongBits(other.d))
                return false;
            if (s == null && other.s != null) {
                return false;
            } else return s.equals(other.s);
        }

        private BetterListsTests getOuterType() {
            return BetterListsTests.this;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + getOuterType().hashCode();
            long temp;
            temp = Double.doubleToLongBits(d);
            result = prime * result + (int) (temp ^ (temp >>> 32));
            result = prime * result + ((s == null) ? 0 : s.hashCode());
            return result;
        }
    }

    @Test
    public void testAll() {
        ArrayList<Dummy> al = new ArrayList<>();
        al.add(new Dummy(1d, "hello"));
        al.add(new Dummy(2d, "test"));
        al.add(new Dummy(2d, "hello"));

        BetterArrayList<Dummy> bal = BetterArrayList.fromList(al);

        Assert.assertTrue(bal.all(du -> du.d > 0));
        Assert.assertFalse(bal.all(du -> du.d > 1));
    }

    @Test
    public void testAny() {
        BetterArrayList<Dummy> bal = new BetterArrayList<>();
        bal.add(new Dummy(1d, "hello"));
        bal.add(new Dummy(2d, "test"));
        bal.add(new Dummy(2d, "hello"));

        Assert.assertTrue(bal.any(du -> du.s.startsWith("t")));
        Assert.assertFalse(bal.any(du -> du.s.startsWith("b")));
    }

    @Test
    public void testCount() {
        BetterArrayList<Dummy> bal = new BetterArrayList<>();
        bal.add(new Dummy(1d, "hello"));
        bal.add(new Dummy(2d, "test"));
        bal.add(new Dummy(2d, "hello"));

        Assert.assertEquals(3, bal.count());
        Assert.assertEquals(2, bal.count(du -> du.s.length() > 4));
    }

    @Test
    public void testExclude() {
        BetterArrayList<Dummy> bal1 = new BetterArrayList<>();
        bal1.add(new Dummy(1d, "hello"));
        bal1.add(new Dummy(2d, "test"));
        bal1.add(new Dummy(3d, "hello"));
        bal1.add(new Dummy(4d, "test"));

        BetterArrayList<Dummy> bal2 = new BetterArrayList<>();
        bal2.add(new Dummy(2d, "test"));
        bal2.add(new Dummy(3d, "hello"));
        bal2.add(new Dummy(5d, "test"));

        BetterArrayList<Dummy> bal3 = (BetterArrayList<Dummy>) bal1.exclusion(bal2);
        Assert.assertEquals(2, bal3.size());
        Assert.assertEquals(bal1.get(0), bal3.get(0));
        Assert.assertEquals(bal1.get(3), bal3.get(1));
    }

    @Test
    public void testFirst() {
        BetterArrayList<Dummy> bal = new BetterArrayList<>();
        bal.add(new Dummy(1d, "hello"));
        bal.add(new Dummy(2d, "test"));
        bal.add(new Dummy(2d, "hello"));

        Assert.assertEquals(bal.get(0), bal.first(du -> du.s.startsWith("h")));

        try {
            bal.first(du -> du.s.startsWith("d"));
            Assert.fail("no error");
        } catch (NoSuchElementException e) {
        }

        Assert.assertEquals(bal.get(0), bal.firstOrDefault(du -> du.s.startsWith("h"), new Dummy(3d, "default")));

        Assert.assertEquals(new Dummy(3d, "default"), bal.firstOrDefault(du -> du.s.startsWith("d"), new Dummy(3d, "default")));
    }

    @Test
    public void testLast() {
        BetterArrayList<Dummy> bal = new BetterArrayList<>();
        bal.add(new Dummy(1d, "hello"));
        bal.add(new Dummy(2d, "test"));
        bal.add(new Dummy(2d, "hello"));

        Assert.assertEquals(bal.get(2), bal.last(du -> du.s.startsWith("h")));

        try {
            bal.last(du -> du.s.startsWith("d"));
            Assert.fail("no error");
        } catch (NoSuchElementException e) {
        }

        Assert.assertEquals(bal.get(2), bal.lastOrDefault(du -> du.s.startsWith("h"), new Dummy(3d, "default")));

        Assert.assertEquals(new Dummy(3d, "default"), bal.lastOrDefault(du -> du.s.startsWith("d"), new Dummy(3d, "default")));
    }

    @Test
    public void testMax() {
        BetterArrayList<Dummy> bal = new BetterArrayList<>();
        bal.add(new Dummy(1d, "hello"));
        bal.add(new Dummy(2d, "test"));
        bal.add(new Dummy(3d, "hello2"));

        Assert.assertEquals(6d, bal.max(du -> (double) du.s.length()), 0.001d);
    }

    @Test
    public void testMean() {
        BetterArrayList<Dummy> bal = new BetterArrayList<>();
        bal.add(new Dummy(1d, "hello"));
        bal.add(new Dummy(2d, "test"));
        bal.add(new Dummy(3d, "hello2"));

        Assert.assertEquals(5d, bal.mean(du -> (double) du.s.length()), 0.001d);
    }

    @Test
    public void testMin() {
        BetterArrayList<Dummy> bal = new BetterArrayList<>();
        bal.add(new Dummy(1d, "hello"));
        bal.add(new Dummy(2d, "test"));
        bal.add(new Dummy(3d, "hello2"));

        Assert.assertEquals(4d, bal.min(du -> (double) du.s.length()), 0.001d);
    }

    @Test
    public void testOrderBy() {
        BetterArrayList<Dummy> bal1 = new BetterArrayList<>();
        bal1.add(new Dummy(1d, "hello1"));
        bal1.add(new Dummy(2d, "hello2"));
        bal1.add(new Dummy(3d, "hello0"));
        bal1.add(new Dummy(4d, "test"));
        bal1.add(new Dummy(5d, "hello4"));

        BetterArrayList<Dummy> bal2 = (BetterArrayList<Dummy>) bal1.orderBy(c -> c.s);
        Assert.assertNotEquals(bal1, bal2);
        Assert.assertEquals(bal1.get(2), bal2.get(0));
        Assert.assertEquals(bal1.get(0), bal2.get(1));
        Assert.assertEquals(bal1.get(1), bal2.get(2));
        Assert.assertEquals(bal1.get(4), bal2.get(3));
        Assert.assertEquals(bal1.get(3), bal2.get(4));
    }

    @Test
    public void testOrderByDescending() {
        BetterArrayList<Dummy> bal1 = new BetterArrayList<>();
        bal1.add(new Dummy(1d, "hello1"));
        bal1.add(new Dummy(2d, "hello2"));
        bal1.add(new Dummy(3d, "hello0"));
        bal1.add(new Dummy(4d, "test"));
        bal1.add(new Dummy(5d, "hello4"));

        BetterArrayList<Dummy> bal2 = (BetterArrayList<Dummy>) bal1.orderByDescending(c -> c.d);
        Assert.assertNotEquals(bal1, bal2);
        for (int i = 0; i < 5; i++)
            Assert.assertEquals(bal1.get(4 - i), bal2.get(i));
    }

    @Test
    public void testReverse() {
        BetterArrayList<Dummy> bal1 = new BetterArrayList<>();
        bal1.add(new Dummy(1d, "hello"));
        bal1.add(new Dummy(2d, "hello"));
        bal1.add(new Dummy(3d, "hello"));
        bal1.add(new Dummy(4d, "test"));
        bal1.add(new Dummy(5d, "hello"));

        BetterArrayList<Dummy> bal2 = (BetterArrayList<Dummy>) bal1.reverse();
        Assert.assertEquals(5, bal2.size());
        for (int i = 0; i < 5; i++)
            Assert.assertEquals(bal1.get(i), bal2.get(4 - i));
    }

    @Test
    public void testSelect() {
        BetterArrayList<Dummy> bal1 = new BetterArrayList<>();
        bal1.add(new Dummy(1d, "hello"));
        bal1.add(new Dummy(2d, "hello"));
        bal1.add(new Dummy(3d, "hello"));
        bal1.add(new Dummy(4d, "test"));
        bal1.add(new Dummy(5d, "hello"));

        BetterArrayList<Double> bal2 = (BetterArrayList<Double>) bal1.select(du -> du.d);
        Assert.assertEquals(5, bal2.size());
        for (int i = 0; i < 5; i++)
            Assert.assertEquals(bal1.get(i).d, bal2.get(i), 0.0001);
    }

    @Test
    public void testSelectManyArrays() {
        BetterArrayList<Dummy> bal1 = new BetterArrayList<>();
        bal1.add(new Dummy(1d, "hel;lo"));
        bal1.add(new Dummy(2d, "hel;lo"));

        BetterArrayList<String> bal2 = (BetterArrayList<String>) bal1.selectManyArrays(du -> du.s.split(";"));
        Assert.assertEquals(4, bal2.size());
        Assert.assertEquals("hel", bal2.get(0));
        Assert.assertEquals("lo", bal2.get(1));
        Assert.assertEquals("hel", bal2.get(2));
        Assert.assertEquals("lo", bal2.get(3));
    }

    @Test
    public void testSelectMany() {
        BetterArrayList<List<String>> bal1 = new BetterArrayList<>();
        bal1.add(Arrays.asList("hel","lo"));
        bal1.add(Arrays.asList("hel","lo"));

        BetterArrayList<String> bal2 = (BetterArrayList<String>) bal1.selectMany(du -> du);
        Assert.assertEquals(4, bal2.size());
        Assert.assertEquals("hel", bal2.get(0));
        Assert.assertEquals("lo", bal2.get(1));
        Assert.assertEquals("hel", bal2.get(2));
        Assert.assertEquals("lo", bal2.get(3));
    }

    @Test
    public void testSkip() {
        BetterArrayList<Dummy> bal1 = new BetterArrayList<>();
        bal1.add(new Dummy(1d, "hello"));
        bal1.add(new Dummy(2d, "hello"));
        bal1.add(new Dummy(3d, "hello"));
        bal1.add(new Dummy(4d, "test"));
        bal1.add(new Dummy(5d, "hello"));

        Assert.assertEquals(bal1, bal1.skip(0));
        Assert.assertEquals(0, bal1.skip(10).size());

        BetterArrayList<Dummy> bal2 = (BetterArrayList<Dummy>) bal1.skip(2);
        Assert.assertEquals(3, bal2.size());
        for (int i = 0; i < 3; i++)
            Assert.assertEquals(bal1.get(i + 2), bal2.get(i));

        BetterArrayList<Dummy> bal3 = (BetterArrayList<Dummy>) bal1.skipWhile(du -> du.s.startsWith("h"));
        Assert.assertEquals(2, bal3.size());
        for (int i = 0; i < 2; i++)
            Assert.assertEquals(bal1.get(i + 3), bal3.get(i));
    }

    @Test
    public void testSum() {
        BetterArrayList<Dummy> bal = new BetterArrayList<>();
        bal.add(new Dummy(1d, "hello"));
        bal.add(new Dummy(2d, "test"));
        bal.add(new Dummy(3d, "hello2"));

        Assert.assertEquals(6d, bal.sum(du -> du.d), 0.001d);
    }

    @Test
    public void testTake() {
        BetterArrayList<Dummy> bal1 = new BetterArrayList<>();
        bal1.add(new Dummy(1d, "hello"));
        bal1.add(new Dummy(2d, "hello"));
        bal1.add(new Dummy(3d, "hello"));
        bal1.add(new Dummy(4d, "test"));
        bal1.add(new Dummy(5d, "hello"));

        Assert.assertEquals(bal1, bal1.take(10));

        Assert.assertEquals(0, bal1.take(0).size());

        BetterArrayList<Dummy> bal2 = (BetterArrayList<Dummy>) bal1.take(2);
        Assert.assertEquals(2, bal2.size());
        for (int i = 0; i < 2; i++)
            Assert.assertEquals(bal1.get(i), bal2.get(i));

        BetterArrayList<Dummy> bal3 = (BetterArrayList<Dummy>) bal1.takeWhile(du -> du.s.startsWith("h"));
        Assert.assertEquals(3, bal3.size());
        for (int i = 0; i < 3; i++)
            Assert.assertEquals(bal1.get(i), bal3.get(i));
    }

    @Test
    public void testUnion() {
        BetterArrayList<Dummy> bal1 = new BetterArrayList<>();
        bal1.add(new Dummy(1d, "hello"));
        bal1.add(new Dummy(2d, "test"));
        bal1.add(new Dummy(3d, "hello"));
        bal1.add(new Dummy(4d, "test"));

        BetterArrayList<Dummy> bal2 = new BetterArrayList<>();
        bal2.add(new Dummy(2d, "test"));
        bal2.add(new Dummy(3d, "hello"));
        bal2.add(new Dummy(5d, "test"));

        BetterArrayList<Dummy> bal3 = (BetterArrayList<Dummy>) bal1.union(bal2);
        Assert.assertEquals(2, bal3.size());
        Assert.assertEquals(bal1.get(1), bal3.get(0));
        Assert.assertEquals(bal1.get(2), bal3.get(1));
    }

    @Test
    public void testWhere() {
        BetterArrayList<Dummy> bal = new BetterArrayList<>();
        bal.add(new Dummy(1d, "hello"));
        bal.add(new Dummy(2d, "test"));
        bal.add(new Dummy(3d, "hello"));

        BetterArrayList<Dummy> bal2 = (BetterArrayList<Dummy>) bal.where(du -> du.s.startsWith("h"));

        Assert.assertEquals(2, bal2.size());
        Assert.assertEquals(new Dummy(1d, "hello"), bal2.get(0));
        Assert.assertEquals(new Dummy(3d, "hello"), bal2.get(1));
    }
}
