package com.anastasko.lnucompass.sync.converter;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.List;

public class BitsTokenizer extends CanPrint {

    private List<Boolean> bits;

    private int ind = 0;

    public BitsTokenizer(List<Boolean> bits) {
        this.bits = bits;
    }

    public List<Boolean> nextList() {
        int len = Integer.parseInt("" + nextNumber());
        List<Boolean> result = new ArrayList<>();
        result.addAll(bits.subList(ind, ind + len));
        ind += len;
        print("take array " + result);
        return result;
    }

    public Long nextNumber() {
        int len = Integer.valueOf("" + bitsToNumber(bits.subList(ind, ind+Converter.START_BITS)));
        print("----> take number " + len);
        ind += Converter.START_BITS;
        Long result = bitsToNumber(bits.subList(ind, ind + len));
        ind += len;
        print("take number " + result);
        return result;
    }

    public boolean nextBoolean() {
        boolean bit = bits.get(ind++);
        print("take bit {" + bit + "}");
        return bit;
    }

    private Long bitsToNumber(List<Boolean> bits) {
        Long ans = 0L;
        for(Boolean bit : bits) {
            ans <<= 1;
            if (bit) ans |= 1;
        }
        return ans;
    }

}
