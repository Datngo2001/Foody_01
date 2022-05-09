package hcmute.edu.vn.foody01;

import hcmute.edu.vn.foody01.model.Dish;
import hcmute.edu.vn.foody01.model.Store;
import hcmute.edu.vn.foody01.model.User;

public interface Goto {
    public void GotoMenu(Store store);
    public void GotoBooking(Dish dish);
    public void GotoEditProfile(User user);
    public void GotoProfile();
}
