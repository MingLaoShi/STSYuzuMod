package baModDeveloper.cards;

import baModDeveloper.action.YUZUFilteredDrawCardAction;
import baModDeveloper.character.YuzuCharacter;
import baModDeveloper.helper.ModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class YUZUShootingPreparation extends YUZUCustomCard{
    public static final String ID= ModHelper.makePath("ShootingPreparation");
    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME=CARD_STRINGS.NAME;
    private static final String IMG_PATH=ModHelper.makeCardImagePath(ID);
    private static final int COST=1;
    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE=CardType.SKILL;
    private static final CardColor COLOR= YuzuCharacter.PlayerClass.YUZU_CARD;
    private static final CardTarget TARGET=CardTarget.SELF;
    private static final CardRarity RARITY=CardRarity.RARE;

    public YUZUShootingPreparation() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber=this.magicNumber=2;
        this.exhaust=true;
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeMagicNumber(1);
    }

    @Override
    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new YUZUFilteredDrawCardAction(this.magicNumber, this::filter, true, new AbstractGameAction() {
            @Override
            public void update() {
                if(!YUZUFilteredDrawCardAction.drawnCards.isEmpty()){
                    AbstractCard card=YUZUFilteredDrawCardAction.drawnCards.get(0);
                    for(int i=1;i<YUZUFilteredDrawCardAction.drawnCards.size();i++){
                        if(YUZUFilteredDrawCardAction.drawnCards.get(i).costForTurn>card.costForTurn){
                            card=YUZUFilteredDrawCardAction.drawnCards.get(i);
                        }
                    }
                    card.setCostForTurn(0);
                }
                this.isDone=true;
            }
        }));

    }

    private boolean filter(AbstractCard card) {
        return card.type==CardType.ATTACK;
    }


}
