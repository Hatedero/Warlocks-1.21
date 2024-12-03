package net.hatedero.warlocksmod.capability.abilitiesinterfaces;

public interface ICharge {
    int getCharge();
    void setCharge(int n);

    int getChargePrev();
    void setChargePrev(int n);

    int getChargeMax();
    void setChargeMax(int n);

    int getChargeMin();
    void setChargeMin(int n);
}
