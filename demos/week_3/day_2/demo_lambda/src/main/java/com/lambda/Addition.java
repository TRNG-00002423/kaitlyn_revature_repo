package com.lambda;

import java.io.Serializable;

public interface Addition extends Serializable {

    int add(int a, int b);

}

// An interface with exactly one method is a functional interface.

// In Java, a marker interface is an interface that has no methods inside.
// e.g. Serializable, Clonable, Remote
// So, when you extend Serializable, for example, there are no methods that need
// to be added.
