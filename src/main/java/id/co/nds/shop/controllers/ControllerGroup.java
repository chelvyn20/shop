package id.co.nds.shop.controllers;

public class ControllerGroup {

    public interface PostingNew {}

    public interface GettingAll {}

    public interface GettingAllByCriteria {}

    public interface GettingById extends RequestMethodById {}

    public interface UpdatingById extends RequestMethodById {}

    public interface DeletingById extends RequestMethodById {}

    public interface RequestMethodById {}
}
