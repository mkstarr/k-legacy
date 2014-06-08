// Copyright (c) 2013-2014 K Team. All Rights Reserved.
package org.kframework.backend.java.kil;

import java.math.BigInteger;

import org.kframework.backend.java.symbolic.BuiltinFunction;
import org.kframework.krun.api.io.FileSystem;

/**
 * An object containing context specific to a particular configuration.
 */
public class TermContext {

    private BigInteger counter = BigInteger.ZERO;
    private final Definition def;
    private final FileSystem fs;
    
    public final BuiltinFunction builtins;
    public final ConstrainedTerm.Data constrainedTermData;

    private TermContext(Definition def, ConstrainedTerm.Data constrainedTermData, FileSystem fs) {
        this.def = def;
        this.builtins = new BuiltinFunction(def);
        this.fs = fs;
        this.constrainedTermData = constrainedTermData;
    }

    /**
     * Only used when the Term is part of a Definition instead of part of a
     * ConstrainedTerm.
     */
    public static TermContext of(Definition def, ConstrainedTerm.Data constrainedTermData) {
        return new TermContext(def, constrainedTermData, null);
    }

    public static TermContext of(Definition def, ConstrainedTerm.Data constrainedTermData,
            FileSystem fs) {
        return new TermContext(def, constrainedTermData, fs);
    }

    public static TermContext of(Definition def) {
        return new TermContext(def, null, null);
    }

    public static TermContext of(Definition def, FileSystem fs) {
        return new TermContext(def, null, fs);
    }

    public BigInteger getCounter() {
        return counter;
    }

    public void setCounter(BigInteger counter) {
        this.counter = counter;
    }

    public BigInteger incrementCounter() {
        counter = this.counter.add(BigInteger.ONE);
        return counter;
    }

    public Definition definition() {
        return def;
    }

    public FileSystem fileSystem() {
        return fs;
    }
}
