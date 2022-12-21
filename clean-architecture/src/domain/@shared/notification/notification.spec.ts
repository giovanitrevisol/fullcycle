import Notification from "./notification";

describe("Unit tests for notifications", () => {

    it("should create errors", () => {
        const notification = new Notification();

        //CUSTOMERS        
        const error = {
            message: "error message",
            context: "customer",
        }
        notification.addError(error);

        expect(notification.messages("customer")).toBe("customer: error message,");

        const error2 = {
            message: "error message2",
            context: "customer",
        }
        notification.addError(error2);

        expect(notification.messages("customer")).toBe(
            "customer: error message,customer: error message2,"
        );

        const error3 = {
            message: "error message3",
            context: "order",
        }
        notification.addError(error3);

        expect(notification.messages("customer")).toBe(
            "customer: error message,customer: error message2,"
        );

        expect(notification.messages()).toBe(
            "customer: error message,customer: error message2,order: error message3,"
        );

        //PRODUCTS
        const error4 = {
            message: "error message",
            context: "product",
        }
        notification.addError(error4);

        expect(notification.messages("product")).toBe("product: error message,");

        const error5 = {
            message: "error message2",
            context: "product",
        }
        notification.addError(error5);

        expect(notification.messages("product")).toBe(
            "product: error message,product: error message2,"
        );

        const error6 = {
            message: "error message3",
            context: "order",
        }
        notification.addError(error6);

        expect(notification.messages("product")).toBe(
            "product: error message,product: error message2,"
        );

        expect(notification.messages()).toBe(
            "customer: error message,customer: error message2,order: error message3,product: error message,product: error message2,order: error message3,"
        );

    });

    it("should check if notification has at least one error", () => {
        const notification = new Notification();
        const error = {
            message: "error message",
            context: "customer",
        }
        notification.addError(error);

        const error2 = {
            message: "error message",
            context: "product",
        }
        notification.addError(error2);

        expect(notification.hasErrors()).toBe(true);
    });

    it("should get all errors", () => {
        const notification = new Notification();
        const error = {
            message: "error message",
            context: "customer",
        }
        notification.addError(error);

        const error2 = {
            message: "error message",
            context: "product",
        }
        notification.addError(error2);

        expect(notification.getErrors()).toEqual([error,error2]);
    });    

});