package baModDeveloper.cards;

import baModDeveloper.character.YuzuCharacter;
import baModDeveloper.helper.ModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class YUZUAlternativeOptions extends YUZUCustomCard{
    public static final String ID= ModHelper.makePath("AlternativeOptions");
    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME=CARD_STRINGS.NAME;
    private static final String IMG_PATH=ModHelper.makeCardImagePath(ID);
    private static final int COST=1;
    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE=CardType.SKILL;
    private static final CardColor COLOR= YuzuCharacter.PlayerClass.YUZU_CARD;
    private static final CardTarget TARGET=CardTarget.NONE;
    private static final CardRarity RARITY=CardRarity.RARE;

    public YUZUAlternativeOptions() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.exhaust=true;

    }

    @Override
    protected void upgradeMethod() {
        this.upgradeBaseCost(0);
    }

    @Override
    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new AbstractGameAction() {

            @Override
            public void update() {
                AbstractDungeon.player.hand.group.stream().filter(card -> card.selfRetain&&card.costForTurn>=0)
                        .forEach(card -> card.setCostForTurn(Math.max(card.costForTurn-1,0)));
                this.isDone=true;
            }
        });
    }

    @Override
    public void masterUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new AbstractGameAction() {

            @Override
            public void update() {
                AbstractDungeon.player.hand.group.stream().filter(card->card.costForTurn>=0)
                        .forEach(card -> card.setCostForTurn(Math.max(card.costForTurn-1,0)));
                this.isDone=true;
            }
        });
    }
}
