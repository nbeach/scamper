describe('navigationMenuSpec', function() {

    var mockLocation;
    var mockNavigationMenuService;
    var mockPromise;
    var mockMenuItems;

    beforeEach(function() {

        mockLocation = {
            path: function() {}
        };

        mockNavigationMenuService = {
            get: function() {}
        };

        mockPromise = {
            then: function() {}
        };

        mockMenuItems = [ {
                title: "Transactions",
                glyph: "glyphicon-list",
                path: "/transaction",
                children: [
                    {
                        title: "Import",
                        glyph: null,
                        path: "/import",
                        children: null
                    }
                ]
            },
            {
                title: "Import",
                glyph: null,
                path: "/import",
                children: null
            }
        ];

        module('scamperApp');
    });

    function injectAndInitController() {
        spyOn(mockNavigationMenuService, 'get').andReturn(mockPromise);
        spyOn(mockPromise, 'then');

        var navigationMenuCtrl;
        inject(function ($controller) {
            navigationMenuCtrl = $controller('navigationMenuCtrl', {$location: mockLocation, NavigationMenuService: mockNavigationMenuService});
        });

        var promiseCallback = mockPromise.then.argsForCall[0][0];
        promiseCallback(mockMenuItems);

        return navigationMenuCtrl;
    }

    it('gets the menu items from the service', function () {
        var controller = injectAndInitController();

        expect(mockNavigationMenuService.get).toHaveBeenCalled();
        expect(mockPromise.then).toHaveBeenCalled();
        expect(controller.getMenuItems()).toBe(mockMenuItems);
    });


    function genericIsActiveMenuItemTest(mockMenuItemIndex, controllerMenuItemIndex, expectedResult) {
        spyOn(mockLocation, 'path').andReturn(mockMenuItems[mockMenuItemIndex].path);

        var controller = injectAndInitController();
        var menuItemIsActive = controller.isActiveMenuItem(controllerMenuItemIndex);

        expect(mockNavigationMenuService.get).toHaveBeenCalled();
        expect(mockPromise.then).toHaveBeenCalled();
        expect(mockLocation.path).toHaveBeenCalled();
        expect(menuItemIsActive).toBe(expectedResult);
    }

    it('tells if a menu item is active', function () {
        genericIsActiveMenuItemTest(0, 0, true)
    });

    it('tells if a menu item is not active', function () {
        genericIsActiveMenuItemTest(0, 1, false)
    });

    it('tells if a menu item has child items', function () {
        var controller = injectAndInitController();
        expect(controller.hasChildren(mockMenuItems[0])).toBe(true);
    });

    it('tells if a menu item does not have child items', function () {
        var controller = injectAndInitController();
        expect(controller.hasChildren(mockMenuItems[1])).toBe(false);
    });

    it('tells if a menu item has a glyphicon', function () {
        var controller = injectAndInitController();
        expect(controller.hasGlyph(mockMenuItems[0])).toBe(true);
    });

    it('tells if a menu item does not have a glyphicon', function () {
        var controller = injectAndInitController();
        expect(controller.hasGlyph(mockMenuItems[1])).toBe(false);
    });

});