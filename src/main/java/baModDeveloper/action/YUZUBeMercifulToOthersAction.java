package baModDeveloper.action;

import baModDeveloper.cards.YUZUBeMercifulToOthers;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class YUZUBeMercifulToOthersAction extends AbstractGameAction {
    AbstractCard card;
    public YUZUBeMercifulToOthersAction(AbstractMonster target ,AbstractCard card){
        this.target=target;
        this.card=card;
    }
    @Override
    public void update() {
        card.calculateCardDamage((AbstractMonster) this.target);
        int damage=card.damage;
        card.damage=Math.min(card.damage,target.currentHealth+target.currentBlock-1);
        addToTop(new DamageAction(target,new DamageInfo(AbstractDungeon.player,card.damage),AttackEffect.BLUNT_HEAVY));
        this.isDone=true;
    }
}
