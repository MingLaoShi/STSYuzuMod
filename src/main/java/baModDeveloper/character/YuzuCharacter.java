package baModDeveloper.character;

import baModDeveloper.YuzuMod;
import baModDeveloper.cards.YUZUCamouflage;
import baModDeveloper.cards.YUZUDefend;
import baModDeveloper.cards.YUZUDesignShooting;
import baModDeveloper.cards.YUZUStrike;
import baModDeveloper.helper.ModHelper;
import baModDeveloper.panel.YUZUCriticalRatePanel;
import baModDeveloper.relic.YUZUSight;
import basemod.abstracts.CustomPlayer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.city.Vampires;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.screens.CharSelectInfo;

import java.util.ArrayList;

public class YuzuCharacter extends CustomPlayer {
    private static final String YUZU_CHARACTER_SHOULDER_1= ModHelper.makeImgPath("character","shoulder2");
    private static final String YUZU_CHARACTER_SHOULDER_2=ModHelper.makeImgPath("character","shoulder2");
    private static final String YUZU_CHARACTER_CORPSE=ModHelper.makeImgPath("character","corpse");
    private static final String[] ORB_TEXTURES = new String[]{
            ModHelper.makeImgPath("UI/orb", "layer1"),
            ModHelper.makeImgPath("UI/orb", "layer2"),
            ModHelper.makeImgPath("UI/orb", "layer3"),
            ModHelper.makeImgPath("UI/orb", "layer4"),
            ModHelper.makeImgPath("UI/orb", "layer5"),
            ModHelper.makeImgPath("UI/orb", "layer6"),
            ModHelper.makeImgPath("UI/orb", "layer1d"),
            ModHelper.makeImgPath("UI/orb", "layer2d"),
            ModHelper.makeImgPath("UI/orb", "layer3d"),
            ModHelper.makeImgPath("UI/orb", "layer4d"),
            ModHelper.makeImgPath("UI/orb", "layer5d")
    };
    private static final String ORB_VFX=ModHelper.makeImgPath("UI/orb","vfx");
    private static final String ORB_MARK=ModHelper.makeImgPath("UI/orb","mark");
    private static final float[] LAYER_SPEED = new float[]{-40.0F, -32.0F, 20.0F, -20.0F, 0.0F, -10.0F, -8.0F, 5.0F,
            -5.0F, 0.0F};
    private static final CharacterStrings characterStrings = CardCrawlGame.languagePack
            .getCharacterString(ModHelper.makePath("YUZU"));
    private static final String CHAR_IMG=ModHelper.makeImgPath("character","yuzu");

    public YUZUCriticalRatePanel getCriticalRatePanel() {
        return criticalRatePanel;
    }

    private YUZUCriticalRatePanel criticalRatePanel;
    public YuzuCharacter(String name) {
        super(name, PlayerClass.YUZU, ORB_TEXTURES, ORB_VFX, LAYER_SPEED,null,null);
        this.initializeClass(CHAR_IMG,YUZU_CHARACTER_SHOULDER_2,YUZU_CHARACTER_SHOULDER_1,YUZU_CHARACTER_CORPSE,getLoadout(),0.0F,0.0F,200.0F,200.0F,new EnergyManager(3));
        this.criticalRatePanel=new YUZUCriticalRatePanel(this.hb.x,this.hb.y+100.0F* Settings.scale,-480 * Settings.scale, 200 * Settings.scale,null,true);
        this.criticalRatePanel.show();
    }

    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> deck=new ArrayList<>();
        deck.add(YUZUStrike.ID);
        deck.add(YUZUStrike.ID);
        deck.add(YUZUStrike.ID);
        deck.add(YUZUStrike.ID);
        deck.add(YUZUDefend.ID);
        deck.add(YUZUDefend.ID);
        deck.add(YUZUDefend.ID);
        deck.add(YUZUDefend.ID);
        deck.add(YUZUCamouflage.ID);
//        deck.add(ModHelper.makePath(ModHelper.getRandomShootingCardId()));
        deck.add(YUZUDesignShooting.ID);
        return deck;
    }

    @Override
    public ArrayList<String> getStartingRelics() {
        ArrayList<String> relics=new ArrayList<>();
        relics.add(YUZUSight.ID);
        return relics;
    }

    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(characterStrings.NAMES[0], characterStrings.TEXT[0], 75, 75, 0, 99, 5, this,
                this.getStartingRelics(), this.getStartingDeck(), false);    }

    @Override
    public String getTitle(AbstractPlayer.PlayerClass playerClass) {
        return characterStrings.NAMES[0];
    }


    @Override
    public AbstractCard.CardColor getCardColor() {
        return PlayerClass.YUZU_CARD;
    }

    @Override
    public Color getCardRenderColor() {
        return YuzuMod.YUZUColor.cpy();
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return new YUZUDesignShooting();
    }

    @Override
    public Color getCardTrailColor() {
        return YuzuMod.YUZUColor.cpy();
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 7;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {

    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return null;
    }

    @Override
    public String getLocalizedCharacterName() {
        return characterStrings.NAMES[0];
    }

    @Override
    public AbstractPlayer newInstance() {
        return new YuzuCharacter(this.name);
    }

    @Override
    public String getSpireHeartText() {
        return characterStrings.TEXT[1];
    }

    @Override
    public Color getSlashAttackColor() {
        return YuzuMod.YUZUColor.cpy();
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{AbstractGameAction.AttackEffect.SLASH_HEAVY,
                AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_DIAGONAL,
                AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE,
                AbstractGameAction.AttackEffect.SLASH_DIAGONAL};
    }

    @Override
    public String getVampireText() {
        return Vampires.DESCRIPTIONS[0];
    }

    public static class PlayerClass {
        @SpireEnum
        public static AbstractPlayer.PlayerClass YUZU;

        @SpireEnum(name = "YUZUCARD")
        public static AbstractCard.CardColor YUZU_CARD;

        @SpireEnum(name = "YUZUCARD")
        public static CardLibrary.LibraryType YUZU_LIBRARY;

    }

    @Override
    public void update() {
        super.update();
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT)
            this.criticalRatePanel.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT)
            this.criticalRatePanel.render(sb);
    }

    @Override
    public void preBattlePrep() {
        super.preBattlePrep();
        this.criticalRatePanel.reset();
    }

    @Override
    public void applyEndOfTurnTriggers() {
        super.applyEndOfTurnTriggers();
        this.getCriticalRatePanel().resetModifierMax();
    }
}
