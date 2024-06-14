package baModDeveloper.cards;

import baModDeveloper.helper.ModHelper;
import baModDeveloper.character.YuzuCharacter;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class YUZUGiveBestShot extends YUZUCustomCard{
    public static final String ID= ModHelper.makePath("GiveBestShot");
    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME=CARD_STRINGS.NAME;
    private static final String IMG_PATH=ModHelper.makeCardImagePath(ID);
    private static final int COST=2;
    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE=CardType.ATTACK;
    private static final CardColor COLOR= YuzuCharacter.PlayerClass.YUZU_CARD;
    private static final CardTarget TARGET=CardTarget.ENEMY;
    private static final CardRarity RARITY=CardRarity.UNCOMMON;

    public YUZUGiveBestShot() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage=this.damage=30;
    }

    @Override
    protected void upgradeMethod() {
        upgradeDamage(5);
    }

    @Override
    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DamageAction(abstractMonster,new DamageInfo(abstractPlayer,this.damage), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                for(AbstractCard c: AbstractDungeon.player.hand.group){
                    addToTop(new ExhaustSpecificCardAction(c,AbstractDungeon.player.hand,true));
                }
                this.isDone=true;
            }
        });
    }

    @Override
    public void masterUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DamageAction(abstractMonster,new DamageInfo(abstractPlayer,this.damage), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                for(AbstractCard c:AbstractDungeon.player.hand.group){
                    if(c.type==CardType.ATTACK){
                        addToTop(new ExhaustSpecificCardAction(c,AbstractDungeon.player.hand,true));
                    }
                }
                this.isDone=true;
            }
        });
    }
}
