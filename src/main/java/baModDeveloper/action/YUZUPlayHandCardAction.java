package baModDeveloper.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class YUZUPlayHandCardAction extends AbstractGameAction {
    public AbstractCard card;
    private AbstractPlayer p;
    private boolean ignoreDeath;
    private int numberOfConnections;
    private boolean blockTheOriginalEffect;

    public YUZUPlayHandCardAction(AbstractCard card, AbstractCreature target, boolean randomTarget, boolean ignoreDeath, int numberOfConnections, boolean blockTheOriginalEffect) {
        this.card = card;
        this.p = AbstractDungeon.player;
        this.target = target;
        this.ignoreDeath = ignoreDeath;
        this.numberOfConnections = numberOfConnections;
        this.blockTheOriginalEffect = blockTheOriginalEffect;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    YUZUPlayHandCardAction(AbstractCard card, AbstractCreature target, boolean randomTarget, boolean ignoreDeath) {
        this(card, target, randomTarget, ignoreDeath, 0, false);
    }

    public YUZUPlayHandCardAction(AbstractCard card, AbstractCreature target) {
        this(card, target, false, true);
    }

    public YUZUPlayHandCardAction(AbstractCard card, AbstractCreature target, boolean ignoreDeath) {
        this(card, target, false, ignoreDeath);
    }

    public YUZUPlayHandCardAction(AbstractCard card, AbstractCreature target, int numberOfConnections) {
        this(card, target, false, true, numberOfConnections, false);
    }

    public YUZUPlayHandCardAction(AbstractCard card, AbstractCreature target, int numberOfConnections, boolean blockTheOriginalEffect) {
        this(card, target, false, true, numberOfConnections, blockTheOriginalEffect);
    }

    @Override
    public void update() {
        if (this.target == null) {
            this.target = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true);
        }
        if (!card.isInAutoplay) {
            this.card.applyPowers();
            this.card.calculateCardDamage((AbstractMonster) this.target);
            AbstractDungeon.getCurrRoom().souls.remove(this.card);

            if (this.target == null || this.target.isDeadOrEscaped()) {
                addToTop((AbstractGameAction) new NewQueueCardAction(card, true, false, true));
            } else {
                addToTop((AbstractGameAction) new NewQueueCardAction(card, this.target, false, true));
            }
        }
        this.isDone = true;
    }
}
