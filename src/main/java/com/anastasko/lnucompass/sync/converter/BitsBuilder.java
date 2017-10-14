package com.anastasko.lnucompass.sync.converter;

import com.anastasko.lnucompass.validation.exceptions.ServiceException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BitsBuilder extends CanPrint {

    private List<Boolean> bits = new ArrayList<>();

    private List<Boolean> numberToBits(Long number) {
        List<Boolean> bits = new ArrayList<>();
        while (number != 0) {
            bits.add((number & 1) == 1);
            number >>= 1;
        }
        Collections.reverse(bits);
        if (bits.size() > 64) throw new ServiceException("number to bits len > 64");
        return bits;
    }

    private List<Boolean> numberToBits(Long number, int cntBits){
        List<Boolean> bits = numberToBits(number);
        if (bits.size() > cntBits)
            throw new ServiceException("numberToBits > cntBits");
        Collections.reverse(bits);
        while (bits.size() < cntBits) {
            bits.add(false);
        }
        Collections.reverse(bits);
        return bits;
    }

    public void put(Long number) {
        List<Boolean> bits2 = numberToBits(number);
        List<Boolean> bits1 = numberToBits(bits2.size() + 0L, 6);
        print("put number " + number);
        print("----> put bits " + bits1);
        bits.addAll(bits1);
        print("----> put bits " + bits2);
        bits.addAll(bits2);
    }

    public void put(List<Boolean> bits3){
        List<Boolean> bits2 = numberToBits(bits3.size() + 0L);
        List<Boolean> bits1 = numberToBits(bits2.size() + 0L, 6);
        print("put bits");
        print("----> put bits " + bits1);
        bits.addAll(bits1);
        print("----> put bits " + bits2);
        bits.addAll(bits2);
        print("----> put bits " + bits3);
        bits.addAll(bits3);
    }

    public void put(Boolean bit){
        print("put bit {" + bit + "}");
        bits.add(bit);
    }

    public List<Boolean> getBits() {
        return bits;
    }

}