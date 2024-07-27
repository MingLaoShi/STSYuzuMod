package YUZUMod.cards;

import YUZUMod.character.YuzuCharacter;
import YUZUMod.helper.ModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class YUZUClaustrophobia extends YUZUCustomCard{
    public static final String ID= ModHelper.makePath("Claustrophobia");
    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME=CARD_STRINGS.NAME;
    private static final String IMG_PATH=ModHelper.makeCardImagePath(ID);
    private static final int COST=1;
    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE=CardType.ATTACK;
    private static final CardColor COLOR= YuzuCharacter.PlayerClass.YUZU_CARD;
    private static final CardTarget TARGET=CardTarget.ENEMY;
    private static final CardRarity RARITY=CardRarity.COMMON;

    public YUZUClaustrophobia() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage=this.damage=5;
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeDamage(7);
    }

    @Override
    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new AbstractGameAction() {
            int damage=YUZUClaustrophobia.this.damage;
            @Override
            public void update() {
                addToTop(new DamageAction(abstractMonster,new DamageInfo(abstractPlayer,damage),AttackEffect.SLASH_DIAGONAL));
                if(AbstractDungeon.player.currentBlock==0){
                    addToTop(new DamageAction(abstractMonster,new DamageInfo(abstractPlayer,damage),AttackEffect.SLASH_DIAGONAL));
                }
                if(abstractMonster.currentBlock==0){
                    addToTop(new DamageAction(abstractMonster,new DamageInfo(abstractPlayer,damage),AttackEffect.SLASH_DIAGONAL));
                }
                this.isDone=true;
            }
        });
    }


}
