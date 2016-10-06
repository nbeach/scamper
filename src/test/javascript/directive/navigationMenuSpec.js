import controller from '../../../main/javascript/directive/navigationMenu';

describe('navigationMenuSpec', function() {

    var mockLocation;
    var mockNavigationMenuService;
    var mockPromise;
    var mockMenuItems;
    var controller;

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

        controller = new controller(mockLocation, mockMenuItems);
    });


    function genericIsActiveMenuItemTest(mockMenuItemIndex, controllerMenuItemIndex, expectedResult) {
        spyOn(mockLocation, 'path').and.returnValue(mockMenuItems[mockMenuItemIndex].path);

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
        expect(controller.hasChildren(mockMenuItems[0])).toBe(true);
    });

    it('tells if a menu item does not have child items', function () {
        expect(controller.hasChildren(mockMenuItems[1])).toBe(false);
    });

    it('tells if a menu item has a glyphicon', function () {
        expect(controller.hasGlyph(mockMenuItems[0])).toBe(true);
    });

    it('tells if a menu item does not have a glyphicon', function () {
        expect(controller.hasGlyph(mockMenuItems[1])).toBe(false);
    });

});