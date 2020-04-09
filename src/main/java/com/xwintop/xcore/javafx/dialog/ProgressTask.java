package com.xwintop.xcore.javafx.dialog;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.concurrent.Task;

public abstract class ProgressTask extends Task<Void> {

    private DoubleProperty maxProgress = new SimpleDoubleProperty(100);

    private DoubleProperty currentProgress = new SimpleDoubleProperty(0);

    public DoubleProperty maxProgressProperty() {
        return maxProgress;
    }

    public DoubleProperty currentProgressProperty() {
        return currentProgress;
    }

    protected double getCurrentProgress() {
        return currentProgress.get();
    }

    protected double getMaxProgress() {
        return maxProgress.get();
    }

    protected void setCurrentProgress(double currentProgress) {
        this.currentProgress.set(currentProgress);
    }

    protected void addProgress(double delta) {
        setCurrentProgress(getCurrentProgress() + delta);
    }

    @Override
    protected Void call() throws Exception {
        execute();
        return null;
    }

    @Override
    public void updateMessage(String message) {
        super.updateMessage(message);
    }

    protected abstract void execute() throws Exception;
}
