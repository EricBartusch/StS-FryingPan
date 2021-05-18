package fryingPan.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import fryingPan.DefaultMod;
import fryingPan.util.TextureLoader;

import static fryingPan.DefaultMod.makeRelicOutlinePath;
import static fryingPan.DefaultMod.makeRelicPath;

public class FryingPan extends CustomRelic {

    public static final String ID = DefaultMod.makeID("FryingPan");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("placeholder_relic2.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("placeholder_relic2.png"));

    public FryingPan() { super(ID, IMG, OUTLINE, RelicTier.SHOP, LandingSound.SOLID); }

    @Override
    public void onPreviewObtainCard(AbstractCard c) {
        if (c.type.equals(AbstractCard.CardType.ATTACK)) {
            if(!c.name.toLowerCase().contains("strike")) {
                c.name = c.name + " Strike";
                c.tags.add(AbstractCard.CardTags.STRIKE);
            }
        }
    }

    @Override
    public void onObtainCard(AbstractCard c) {
        if (c.type.equals(AbstractCard.CardType.ATTACK)) {
            if(!c.name.toLowerCase().contains("strike")) {
                c.name = c.name + " Strike";
                c.tags.add(AbstractCard.CardTags.STRIKE);
            }
        }
    }

    @Override
    public void onEquip() {
        for (AbstractCard c: AbstractDungeon.player.masterDeck.getAttacks().group) {
            if(!c.name.toLowerCase().contains("strike")) {
                c.name = c.name + " Strike";
                c.tags.add(AbstractCard.CardTags.STRIKE);
            }
        }
    }
    @Override
    public float atDamageModify(float damage, AbstractCard c) {
        return c.originalName.toLowerCase().contains("strike") ? damage + 1.0F : damage;
    }


    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
