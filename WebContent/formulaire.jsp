<html>
<body>
    
    
    
    <h2>Formulaire</h2>
    <form:form action="rest/ws/hello" method="POST">
	    <div>
	        <label for="name">Nom :</label>
	        <input type="text" id="name" name="user_name">
	    </div>
	    <div>
	        <label for="mail">e-mail :</label>
	        <input type="email" id="mail" name="user_mail">
	    </div>
	    <div>
	        <label for="msg">Message :</label>
	        <textarea id="msg" name="user_message"></textarea>
	    </div>
	    
	    <div class="form-group">
                <button type="submit" class="btn btn-info">Submit</button>
         </div>
	</form:form>
	
</body>
</html>