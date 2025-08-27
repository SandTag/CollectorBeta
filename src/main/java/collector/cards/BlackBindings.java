package collector.cards;

import collector.powers.DoomPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.EntangleEffect;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;

public class BlackBindings extends AbstractCollectorCard {
    public final static String ID = makeID(BlackBindings.class.getSimpleName());
    // intellij stuff skill, enemy, uncommon, , , , , 2, 1

    public BlackBindings() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 2;
        baseSecondMagic = secondMagic = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new VFXAction(new EntangleEffect(p.hb.cX + 70.0F * Settings.scale, p.hb.cY + 10.0F * Settings.scale, m.hb.cX, m.hb.cY), 0.5F));
        if (m.hasPower(WeakPower.POWER_ID)){
            //applyToEnemy(m, new WeakPower(m, magicNumber, false));
            applyToEnemyTop(m, new DoomPower(m, secondMagic));
        }
        applyToEnemy(m, new WeakPower(m, magicNumber, false));
        applyToEnemy(m, new DoomPower(m, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
        upgradeSecondMagic(2);
    }

    @Override //zhs card text thing
    public void initializeDescriptionCN() {
        super.initializeDescriptionCN();
        if((Settings.language == Settings.GameLanguage.ZHS || Settings.language == Settings.GameLanguage.ZHT) && this.description!=null && this.description.size()>=1 ) {
            for(int i=0; i < this.description.size(); i++){
                if(this.description.get(i).text.equals("，")){
                    StringBuilder sb = new StringBuilder();
                    this.description.get(i-1).text = sb.append(this.description.get(i-1).text).append("，").toString();
                    this.description.remove(i);
                }
            }
        }
    }
}