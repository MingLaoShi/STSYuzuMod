package YUZUMod.modifier;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.ArrayList;
import java.util.List;

public class YUZUCanNotPlayModifier extends AbstractCardModifier {
    public static final String ID = "YUZUCanNotPlayModifier";
    private static List<String> strings=new ArrayList<>();
    static {
        strings.add("不能被打出。");
    }
    @Override
    public AbstractCardModifier makeCopy() {
        return new YUZUCanNotPlayModifier();
    }

    @Override
    public boolean canPlayCard(AbstractCard card) {
        return false;
    }

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }

    @Override
    public List<String> extraDescriptors(AbstractCard card) {
        return strings;
    }
}
