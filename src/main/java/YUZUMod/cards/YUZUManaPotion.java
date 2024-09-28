package YUZUMod.cards;

import YUZUMod.character.YuzuCharacter;
import YUZUMod.helper.ModHelper;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class YUZUManaPotion extends YUZUCustomCard{
    public static final String ID= ModHelper.makePath("ManaPotion");
    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME=CARD_STRINGS.NAME;
    private static final String IMG_PATH=ModHelper.makeCardImagePath(ID);
    private static final int COST=1;
    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE=CardType.SKILL;
    private static final CardColor COLOR= YuzuCharacter.PlayerClass.YUZU_CARD;
    private static final CardTarget TARGET=CardTarget.NONE;
    private static final CardRarity RARITY=CardRarity.UNCOMMON;

    public YUZUManaPotion() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber=this.magicNumber=1;
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeBaseCost(0);
    }

    @Override
    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new AbstractGameAction() {
            float duration=-1.0F;
            ArrayList<AbstractCard> cards=new ArrayList<>();
            boolean firstUpdate=true;
            int amount=YUZUManaPotion.this.magicNumber;
            int totalEnergy=0;
            @Override
            public void update() {
                if(firstUpdate){
                    for(AbstractCard card:AbstractDungeon.player.hand.group){
                        if(card.costForTurn>=2){
                            cards.add(card);
                        }
                    }
                    this.firstUpdate=false;
                    this.totalEnergy=this.cards.size();
                }else{
                    this.duration-= Gdx.graphics.getDeltaTime();
                    if(this.cards.isEmpty()){
                        if(this.totalEnergy>0){
                            AbstractDungeon.player.hand.group.forEach(card -> {
                                card.triggerOnGainEnergy(this.totalEnergy,true);
                            });
                        }
                        this.isDone=true;
                        return;
                    }
                    if(this.duration<0.0F){
                        AbstractCard card=cards.get(0);
                        card.flash();
                        AbstractDungeon.player.gainEnergy(this.amount);
                        AbstractDungeon.actionManager.updateEnergyGain(this.amount);
                        this.duration=0.3F;
                        cards.remove(card);
                    }
                }

            }
        });
    }
}
