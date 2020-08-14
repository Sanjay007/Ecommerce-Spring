import React from "react";
import './main.css';
import api from './../Axios/index';
import axios, { post } from 'axios';

export default class AddProduct extends React.Component {
  constructor() {
    super();
    this.state = {
      file: "",
      productName: "",
      desc: "",
      price: "",
      productDescription: "",
      gender: "",
      color: ""
    };
  }


  handleChange = (event) => {
    console.log(event)
    const { name, value } = event.target;
    const { user } = this.state;
    console.log(name, value, user)
    this.setState({
      user: {
        ...user,
        [name]: value
      }
    });
  }

  handleFile = (ev) => {
    this.setState({ file: ev.target.files[0] })

  }
  submit = (e) => {
    e.preventDefault();
    const { user } = this.state;

    let reqData = {
      "productName": user.productName,
      "productDescription": user.productDescription,
      "price": Number(user.price),
      "gender": user.gender,
      "color": user.color
    }
    console.log(reqData, "oooooooooooo")
    api.addProduct(reqData, this.props.history).then(response => {
      console.log(response.data);
      if (response.data.id) {
        this.fileUpload(this.state.file, response.data.id).then(response=>{
          this.setState({success:"Saved SuccessFully",user:""});

        })
      }else{
        this.setState({error:"Unknown Error Occured"})
      }
    })
  }
  fileUpload = (file, id) => {

    const url = `http://localhost:8080/api/product/${id}/upload`;

    const formData = new FormData();
    formData.append('file', file)
    const config = {
      headers: {
        'content-type': 'multipart/form-data',
        'Authorization': 'Bearer ' + localStorage.getItem('user')
      }
    }
    return post(url, formData, config)
  }
  render() {
    const {success,error}=this.state;

    return (
      <div class="limiter">
        <div class="container-login100">
          <div class="wrap-login100">
            <form action="#" class="product-add-form">
              <div class="product-add">
                <p>Please fill in this form to add product.</p>
                <hr />
                {success?<p>{success}</p>:""}
                {error?<p>{error}</p>:""}
                <label>Product Name</label>
                <input type="text" placeholder="Product Name" name="productName" id="product-name"  required
                  onChange={e => this.handleChange(e)} />

                <label>Product Price</label>
                <input type="number" width="122px" placeholder="Product Price" name="price" id="product-price" required onChange={e => this.handleChange(e)} />
                <br></br>
                <label>Select Color</label>
                <select name="color" onChange={e => this.handleChange(e)}>
                  <option selected disabled name="color"  >Select One</option>
                  <option name="color" value="RED" >Red</option>
                  <option name="color" value="BLUE" >Blue</option>
                </select>

                {/* <label>Select Date</label>
                <input type="date" name="date" id="date" required /> */}

                <label>Select Gender</label><br />
                <input type="radio" id="male" name="gender" value="MALE" onChange={e => this.handleChange(e)} />
                <label for="MALE">Male</label>
                <input type="radio" id="female" name="gender" value="FEMALE" onChange={e => this.handleChange(e)} />
                <label for="FEMALE">Female</label>
                <br />
                <label>Product Description</label>
                <textarea name="productDescription" onChange={e => this.handleChange(e)}>Add Text Here</textarea>
                <label>Upload Multiple image</label>
                <input type="file" name="file" id="file" onChange={ev => this.handleFile(ev)} required></input>

                <hr />
                {/* <input type="checkbox" name="checkbox" id="checkbox" required></input>
                <p>I agree with all terms and conditions</p> */}

                <button type="submit" class="add-tem" onClick={this.submit}>Add</button>
              </div>
            </form>

          </div>
        </div>
      </div>
    );
  }
}
