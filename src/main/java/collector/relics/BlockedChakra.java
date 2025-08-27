package collector.relics;

import basemod.abstracts.CustomRelic;
import collector.CollectorCollection;
import collector.CollectorMod;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import downfall.downfallMod;
import downfall.util.TextureLoader;
import utilityClasses.DFL;

public class BlockedChakra extends CustomRelic {
    public static final String ID = CollectorMod.makeID(BlockedChakra.class.getSimpleName());
    private static final String IMG_PATH = BlockedChakra.class.getSimpleName() + ".png";
    private static final String OUTLINE_IMG_PATH = BlockedChakra.class.getSimpleName() + ".png";

    public BlockedChakra() {
        super(ID, TextureLoader.getTexture(CollectorMod.makeRelicPath(IMG_PATH)), TextureLoader.getTexture(CollectorMod.makeRelicOutlinePath(OUTLINE_IMG_PATH)), RelicTier.BOSS, LandingSound.MAGICAL);
        this.counter = -1;
        getUpdatedDescription();
    }

    @Override
    public void onEquip() {
        AbstractDungeon.player.energy.energyMaster++;
        if (downfallMod.makeCollectorWorse){
            DFL.pl().masterHandSize--;
        }
        getUpdatedDescription();
    }

    @Override
    public void onUnequip() {
        AbstractDungeon.player.energy.energyMaster--;
        if (downfallMod.makeCollectorWorse){
            DFL.pl().masterHandSize++;
        }
    }

    @Override
    public void update(){
        super.update();
        getUpdatedDescription();
    }

    @Override
    public boolean canSpawn() {
        return (!(AbstractDungeon.floorNum <= 2));//Banned from boss swap.
    }

    @Override
    public String getUpdatedDescription() {
        if (downfallMod.makeCollectorWorse){
            return DESCRIPTIONS[1];
        }
        return DESCRIPTIONS[0];
    }
}

