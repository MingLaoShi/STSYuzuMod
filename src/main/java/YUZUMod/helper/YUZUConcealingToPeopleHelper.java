package YUZUMod.helper;

import basemod.BaseMod;
import basemod.interfaces.ISubscriber;
import basemod.interfaces.OnPlayerTurnStartSubscriber;
import basemod.interfaces.PostBattleSubscriber;
import basemod.interfaces.PreMonsterTurnSubscriber;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class YUZUConcealingToPeopleHelper implements ISubscriber, PostBattleSubscriber , PreMonsterTurnSubscriber, OnPlayerTurnStartSubscriber {
    private AbstractMonster monster;
    public YUZUConcealingToPeopleHelper(){
        BaseMod.subscribe(this);
    }


    @Override
    public void receivePostBattle(AbstractRoom abstractRoom) {
        this.monster=null;
    }


    @Override
    public boolean receivePreMonsterTurn(AbstractMonster abstractMonster) {
        if(this.monster!=null){
            return abstractMonster==monster;
        }
        return true;
    }

    @Override
    public void receiveOnPlayerTurnStart() {
        this.monster=null;
    }

    public void setMonster(AbstractMonster monster){
        this.monster=monster;
    }
}
