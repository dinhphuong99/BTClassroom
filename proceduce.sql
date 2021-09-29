USE bankingmanager;
DELIMITER //
CREATE PROCEDURE sp_deposit(IN id INT , IN money DOUBLE)
BEGIN
	UPDATE customers
    SET balance = balance + money
    WHERE customers.id = id;
    INSERT INTO deposits (id, balance, fullday)
    VALUE (id, money, sysdate());
END //

USE bankingmanager;

DELIMITER //
CREATE PROCEDURE sp_withdraw(IN id INT , IN money DOUBLE)
BEGIN
	DECLARE balance1 DOUBLE ;
	SET balance1 = (SELECT c.balance FROM customers c WHERE c.id = id);

	IF (balance1 >= money AND balance1 > 0) THEN  
	SET balance1 = balance1 - money; 

	UPDATE customers
	SET balance = balance1 WHERE customers.id = id;

	INSERT INTO withdraws(id , balance, fullday)
	VALUES(id,money,sysdate());
	END IF;
END //

-- DROP PROCEDURE sp_transfer;
DELIMITER //
CREATE PROCEDURE sp_transfer(IN depoId INT, IN withId INT, IN balance DOUBLE)
BEGIN
	DECLARE deposits_balance DOUBLE;
    DECLARE withdraws_balance DOUBLE;
    SET deposits_balance = (SELECT c.balance FROM customers c WHERE c.id = depoId);
    SET withdraws_balance = (SELECT c.balance FROM customers c WHERE c.id = withId);
    IF (deposits_balance >= (balance + balance * 10/100))
    THEN
    SET deposits_balance = deposits_balance - (balance + balance * 10/100);
    SET withdraws_balance = withdraws_balance + balance;
    UPDATE customers
    SET balance = deposits_balance WHERE customers.id = depoId;
    UPDATE customers
    SET balance = withdraws_balance WHERE customers.id = withId;
    INSERT INTO transfers (money, fees, depoId, withId, fullday)
    VALUES (balance, balance*10/100, depoId, withId, sysdate());
    END IF;
END //
