create trigger order_insert_trigger BEFORE INSERT ON 'order' FOR EACH ROW
begin 
	if new.date < now() then
    SET new.date = curdate();
    end if;
end
