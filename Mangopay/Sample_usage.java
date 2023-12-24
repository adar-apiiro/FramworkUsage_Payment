    import com.mangopay.MangoPayApi;
    import com.mangopay.entities.User;
    import com.mangopay.entities.BankAccount;
    import com.mangopay.core.Pagination;
    import java.util.List;

    // ...

    MangoPayApi api = new MangoPayApi();

    // get some user by id
    User john = api.Users.get(someId);

    // change and update some of his data
    john.setTag(john.getTag() + " - CHANGED");
    api.getUserApi().update(john);

    // get all users (with pagination and sorting)
    Pagination pagination = new Pagination(1, 8); // get 1st page, 8 items per page
    Sorting sort = new Sorting();
    sort.addField("SortingField", SortDirection.asc); // Sorting is an enum, its values: none, asc, desc
    List<User> users = api.getUserApi().getAll(pagination, sort);

    // get his bank accounts
    pagination = new Pagination(2, 10); // get 2nd page, 10 items per page
    List<BankAccount> accounts = api.getUserApi().getBankAccounts(john.Id, pagination, sort);
