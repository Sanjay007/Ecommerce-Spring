import React from "react";
import api from './../Axios/index';
import { useHistory } from "react-router-dom";
import './main.css'

export default class LoginComponent extends React.Component {
	
	constructor() {
		super();
		this.state = {
			username: "",
			password: "",
			error:""
		};
	}

	login = (e) => {
		e.preventDefault();
		const { username, password } = this.state;
		let reqData = {
			"usernameOrEmail": username,
			"password": password,
		}
		api.login(reqData, this.props.history).then((response) => {
               
			localStorage.setItem('user', (response.data.accessToken));

			//console.log("useHistory", history);
		  
			this.props.history.push("/product");
			return response;

		}, (error) => {
			this.setState({error:"Invalid credentials"})
			return "Invalid Credentials"
		});
	}

	onChangeUserName = (e) => {
		this.setState({ username: e.target.value })
	}
	onChangePassword = (e) => {
		this.setState({ password: e.target.value })
	}
	render() {
		const { username, password,error } = this.state;
		return (
			<div class="limiter">
			
				<div class="container-login100">
					<div class="wrap-login100">
						<form class="login100-form validate-form" onSubmit={this.login}>
						{error?<p style={{textAlign:'center'}}>{error}</p>:""}
							<span class="login100-form-title p-b-34">
								Admin Login
							
					</span>
					
							<div class="wrap-input100 rs1-wrap-input100 validate-input m-b-20" data-validate="Type user name">
								<input id="first-name" class="input100" type="text" name="username" placeholder="User name" value={username}
									onChange={e => this.onChangeUserName(e)} />
								<span class="focus-input100"></span>
							</div>
							<div class="wrap-input100 rs2-wrap-input100 validate-input m-b-20" data-validate="Type password">
								<input class="input100" type="password" name="pass" placeholder="Password"
									value={password}
									onChange={e => this.onChangePassword(e)} />
								<span class="focus-input100"></span>
							</div>

							<div class="container-login100-form-btn">
								<button type="submit" class="add-tem">Add</button>
							</div>
						</form>
						<div class="login100-more" style={{ backgroundImage: `url(require("images/login.png"))` }}></div>
					</div>
				</div>
			</div>
		);
	}
}
