package id.co.nds.shop.controllers;

public class ControllerGroup {
    public interface PostingNew {}

    public interface GettingAllByCriteria {}

    public interface GettingById extends RequestMethodById {}

    public interface PuttingById extends RequestMethodById {}

    public interface DeletingById extends RequestMethodById {}

    public interface RequestMethodById {}
}
