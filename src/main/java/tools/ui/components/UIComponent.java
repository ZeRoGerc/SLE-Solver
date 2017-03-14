package tools.ui.components;

import com.sun.istack.internal.NotNull;

import java.awt.*;

public class UIComponent {

    @NotNull
    protected final Component component;

    public UIComponent(@NotNull Component component) {
        this.component = component;
    }

    public Component component() {
        return component;
    }
}
