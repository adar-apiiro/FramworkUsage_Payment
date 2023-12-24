import com.mangopay.MangoPayApi;
import com.mangopay.entities.User;
import com.mangopay.entities.BankAccount;
import com.mangopay.core.Pagination;
import com.mangopay.core.SortDirection;
import com.mangopay.core.Sorting;

import java.util.List;

public class MangoPayExample {

    private MangoPayApi api;

    public MangoPayExample() {
        this.api = new MangoPayApi();
    }

    public void updateUser(String userId, String additionalTag) {
        User user = api.Users.get(userId);
        user.setTag(user.getTag() + " - " + additionalTag);
        api.getUserApi().update(user);
    }

    public List<User> getAllUsers(int page, int itemsPerPage) {
        Pagination pagination = new Pagination(page, itemsPerPage);
        Sorting sort = new Sorting();
        sort.addField("SortingField", SortDirection.asc);
        return api.getUserApi().getAll(pagination, sort);
    }

    public List<BankAccount> getUserBankAccounts(String userId, int page, int itemsPerPage) {
        Pagination pagination = new Pagination(page, itemsPerPage);
        Sorting sort = new Sorting();
        sort.addField("SortingField", SortDirection.asc);
        return api.getUserApi().getBankAccounts(userId, pagination, sort);
    }

    public static void main(String[] args) {
        MangoPayExample mangoPayExample = new MangoPayExample();

        // Example: Update user
        mangoPayExample.updateUser("someId", "CHANGED");

        // Example: Get all users
        List<User> users = mangoPayExample.getAllUsers(1, 8);

        // Example: Get user bank accounts
        List<BankAccount> bankAccounts = mangoPayExample.getUserBankAccounts("someUserId", 2, 10);

    }
}
