package com.xwintop.xcore.javafx.control;

import javafx.beans.NamedArg;
import javafx.scene.control.Spinner;

public class IntegerSpinner extends Spinner<Integer> {

    public IntegerSpinner(
        @NamedArg(value = "min", defaultValue = "0") int min,
        @NamedArg(value = "max", defaultValue = "100") int max,
        @NamedArg(value = "initialValue", defaultValue = "0") int initialValue,
        @NamedArg(value = "amountToStepBy", defaultValue = "1") int amountToStepBy
    ) {
        super(min, max, initialValue, amountToStepBy);
    }
}
