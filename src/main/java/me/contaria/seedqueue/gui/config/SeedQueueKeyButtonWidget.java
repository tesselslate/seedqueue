package me.contaria.seedqueue.gui.config;

import net.minecraft.client.gui.widget.AbstractPressableButtonWidget;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public class SeedQueueKeyButtonWidget extends AbstractPressableButtonWidget {
    private static final Text UNKNOWN_KEY = InputUtil.UNKNOWN_KEY.getLocalizedText();

    private final SeedQueueKeybindingsListWidget.KeyEntry entry;

    public SeedQueueKeyButtonWidget(SeedQueueKeybindingsListWidget.KeyEntry entry) {
        this(entry, UNKNOWN_KEY);
    }

    public SeedQueueKeyButtonWidget(SeedQueueKeybindingsListWidget.KeyEntry entry, Text message) {
        super(0, 0, 75, 20, message);
        this.entry = entry;
    }

    @Override
    public void onPress() {
        this.entry.selectButton(this);
    }

    @Override
    protected MutableText getNarrationMessage() {
        if (UNKNOWN_KEY.equals(this.getMessage())) {
            return new TranslatableText("narrator.controls.unbound", this.entry.title);
        }
        return new TranslatableText("narrator.controls.bound", this.entry.title, this.getMessage());
    }
}