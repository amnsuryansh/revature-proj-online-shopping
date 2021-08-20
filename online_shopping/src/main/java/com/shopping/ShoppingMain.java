package com.shopping;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.app.exception.BusinessException;
import com.shopping.dao.impl.CustomerDAOImpl;
import com.shopping.model.Cart;
import com.shopping.model.Customer;
import com.shopping.model.Employee;
import com.shopping.model.Order;
import com.shopping.model.Product;
import com.shopping.service.CartService;
import com.shopping.service.CustomerService;
import com.shopping.service.EmployeeService;
import com.shopping.service.OrderService;
import com.shopping.service.ProductMethodService;
import com.shopping.service.impl.CartServiceImpl;
import com.shopping.service.impl.CustomerServiceImpl;
import com.shopping.service.impl.EmployeeServiceImpl;
import com.shopping.service.impl.OrderServiceImpl;
import com.shopping.service.impl.ProductMethodServiceImpl;

public class ShoppingMain {

	private static Logger log = Logger.getLogger(ShoppingMain.class);
	//private static int customer_id;

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		log.info("Welcome to Revature Project One -- Console Based Shopping Application");
		log.info("-----------------------------------------------------------------------------");

		int mainchoice = 0;
		do {
			log.info("\nWelcome to the *Main Menu*");
			log.info("------------------------------");

			log.info("1] Login as Customer");
			log.info("2] Login as Employee");
			log.info("3] Register Customer");
			log.info("------------------------------");

			try {
				mainchoice = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				log.info("invalid main menu choice");
			}

			switch (mainchoice) {
			case 1:
				log.info("------------------------------");
				log.info("\nWelcome to Customer Login");
				log.info("------------------------------");
				log.info("Enter Customer email : ");
				String customeremail = scanner.nextLine();
				if (customeremail == null) {
					break;
				}
				log.info("Enter Customer Password : ");
				String customerpassword = scanner.nextLine();
				try {
					CustomerService customerService1 = new CustomerServiceImpl();
					Customer customer = customerService1.isValidCustomer(customeremail, customerpassword);
					if(customer.getFirst_name()==null) {
						log.info("wrong credidentials");
					}
					if(customer.getFirst_name()!=null) {
						log.info("------------------------------");
						log.info("Login Successfull. :)");
						log.info("Welcome " + customer.getFirst_name() + " " + customer.getLast_name());
						
					}
					
					/*if (customer != null) {
						log.info("------------------------------");
						log.info("Login Successfull. :)");
						log.info("Welcome " + customer.getFirst_name() + " " + customer.getLast_name());
						
					}
					else {
						log.info("No customer Found");
					}*/
				} catch (BusinessException e) {
					log.warn(e.getMessage());
				}

//				log.info("Login Successfull. again :)");
				int loginchoice = 0;
				do {
					log.info("------------------------------");
					log.info("What do you wanna do today? :| ");
					log.info("------------------------------");

					log.info("1] Search Products");
					log.info("2] View Cart");
					log.info("3] View Orders");
					log.info("4] LogOut :(");
					
					log.info("------------------------------");
					log.info("Enter valid Choice(1-3)");

					try {
						loginchoice = Integer.parseInt(scanner.nextLine());
					} catch (NumberFormatException e) {
						log.info("invalid login choice");
					}

					switch (loginchoice) {
					case 1:
						log.info("------------------------------");
						log.info("Welcome to product Search");
						log.info("Our Available Products");
						// Product product = new Product();
						ProductMethodService productMethodService = new ProductMethodServiceImpl();
						try {
							List<Product> productList = productMethodService.getAllProducts();
							if (productList != null && productList.size() > 0) {
								log.info("Displayed :");
								productList.stream().forEach(System.out::println);
//								for (Product product : productList) {
//									log.info(product);
//								}
							}
						} catch (BusinessException e) {
							log.warn(e.getMessage());
						}
						log.info("Now you can search products based on...");
						int productsearchchoice = 0;
						do {
							log.info("------------------------------");
							log.info("1] By Product Id");
							log.info("2] By Product Name");
							log.info("3] By Category");
							log.info("4] LogOut :(");
							log.info("Enter valid productchoice(1-4)");
							log.info("------------------------------");

							try {
								productsearchchoice = Integer.parseInt(scanner.nextLine());
							} catch (NumberFormatException e) {
								log.warn(e.getMessage());

							}
							switch (productsearchchoice) {
							case 1:
								int id;
								log.info("Now you will see products based on selected id");
								log.info("Select Id : ");
								try {
									id = Integer.parseInt(scanner.nextLine());
									Product product = productMethodService.getProductById(id);
									if (product != null) {
										log.info("Product with id : " + id + " found Successfully");
										log.info(product);
									}
									int orderchoicebyid = 0;
									do {
										log.info("1] Add Product to cart");
										log.info("2] Previous Menu");
										log.info("Enter Valid order choice(1-2)");
										try {
											orderchoicebyid = Integer.parseInt(scanner.nextLine());
										} catch (NumberFormatException e) {
										}
										switch (orderchoicebyid) {
										case 1:
											
											try {
												
												int customerId = CustomerDAOImpl.ad;
											log.info("Add Quantity : ");
											int quantity = Integer.parseInt(scanner.nextLine());
											CartService cartService = new CartServiceImpl();
									
											int c = cartService.addProductToCartById(id,customerId);
											
											if(c==1) {
												log.info("placed into cart");
											}
											int placeorder=0;
											do {
												log.info("1]View Cart");
												log.info("2] Place Order for cart Items");
												log.info("3] Search for more products");
												log.info("4] Previous Menu");
												log.info("Enter valid choice(1-4)");
												try {
													placeorder = Integer.parseInt(scanner.nextLine());
												} catch (NumberFormatException e) {
												}
												switch(placeorder) {
												case 1:
													log.info("Viewing Cart. -");
													Cart cart =new Cart();
													try {
														CartService cartService1 = new CartServiceImpl();
														List<Cart> cartList = cartService1.viewCart(cart);
														
														double totalCartPrice=0;
														if(cartList.size()>0) {
														
														for(Cart cart1:cartList) {
															log.info(cart1.getProduct().getProduct_name()+"  |  "+cart1.getProduct().getProduct_price());
															totalCartPrice = cartList.stream().mapToDouble(c1->c1.getProduct().getProduct_price()).sum();
														}
														log.info("Total Cart Price : "+totalCartPrice);
														}
														else if(cartList.size()==0) {
															log.info("Your Cart is Empty");
															log.info("Start Shopping");
														}
														
														
														

													} catch (BusinessException e) {
														log.warn(e.getMessage());
													}
													break;
												case 2:
													log.info("Viewing Cart. -");
													Cart newcart =new Cart();
													try {
														CartService cartService1 = new CartServiceImpl();
														List<Cart> cartList = cartService.viewCart(newcart);
														
														double totalCartPrice=0;
														if(cartList.size()>0) {
														
														for(Cart cart1:cartList) {
															log.info(cart1.getProduct().getProduct_name()+"  |  "+cart1.getProduct().getProduct_price());
															totalCartPrice = cartList.stream().mapToDouble(c2->c2.getProduct().getProduct_price()).sum();
														}
														log.info("Total Cart Price : "+totalCartPrice);
														int ordernow=0;
														do {
															log.info("Do you Wish to Place Order for the above products in the card");
															log.info("1] Yes");
															log.info("2] No");
															try {
																ordernow = Integer.parseInt(scanner.nextLine());
															}catch (NumberFormatException e) {
																log.info(e.getMessage());
															}
															
															switch(ordernow) {
															case 1:
																 Order order =new Order();
																OrderService orderservice = new OrderServiceImpl();
																for(Cart cart1:cartList) {
																	log.info(cart1.getProduct().getProduct_name()+"  |  "+cart1.getProduct().getProduct_price());
																	int a=0;
																	a=orderservice.placeOrder(cart1);
																	if(a==1) {
																		log.info("Order Placed" +" with orderId "+order.getOrderId() );
																	}
																}
																break;
															case 2:
																log.info("Returning Back");
																break;
															}
															
															
														}while(ordernow!=2);
														}
														else {
															log.info("Your cart is Empty");
															log.info("Start Shopping");
														}
														
														

													} catch (BusinessException e) {
														log.warn(e.getMessage());
													}

													break;
												case 3:
													log.info("Start searching for more products...  :)");
													break;
												case 4:
													log.info("Returning to previous menu");
													break;
												default:
													log.info("Enter valid choice");
														
												}
											}while(placeorder!=4);
											
											
											
										}catch (NumberFormatException e) {
											log.warn(e.getMessage());
											log.info("Product Id should be digit only... Try Again");
										}
										
											
											break;
										case 2:
											log.info("Returning to previous menu");
											break;
										default:
											log.info(
													"Invalid Search Option... Choice should be only number between"
													+ " 1-2 only. Kindly Retry");
										}

									} while (orderchoicebyid != 2);

								} catch (NumberFormatException e) {
									log.warn(e.getMessage());
									log.info("Product Id should be digit only... Try Again");
								} catch (BusinessException e) {
									log.warn(e.getMessage());
								}
								log.info("proceed by selecting either");
							

								break;
							case 2:
								log.info("Now you will see products based on selected Name");
								log.info("Select name : ");
								try {
									String name = scanner.nextLine();
									Product product = productMethodService.getProductByName(name);
									if (product != null) {
										log.info("Product with name : " + name + " found Successfully");
										log.info(product);
									}
									int orderchoicebyname=0;
									switch (orderchoicebyname) {
									case 1:
//										log.info("1] Add Product to cart");
//										log.info("2] Previous Menu");
//										log.info("Enter Valid order choice(1-2)");
//										try {
//											orderchoicebyname = scanner.nextLine();
//										} catch (NumberFormatException e) {
//										}
										int quantity=0;
										try {
											
											int customerId = CustomerDAOImpl.ad;
										log.info("Add Quantity : ");
										quantity = Integer.parseInt(scanner.nextLine());
										CartService cartService = new CartServiceImpl();
								
										int c = cartService.addProductToCartByName(name,customerId);
										
										if(c==1) {
											log.info("placed into cart");
										}
										
										
										
									}catch (NumberFormatException e) {
										log.warn(e.getMessage());
										log.info("Product Id should be digit only... Try Again");
									}
									
										
										break;
									case 2:
										break;
									default:
										log.info(
												"Invalid Search Option... Choice should be only number between"
												+ " 1-2 only. Kindly Retry");
									} while (orderchoicebyname != 2);
									

								} catch (NumberFormatException e) {
									log.warn(e.getMessage());

									log.info("Product name should be text only... Try Again");
								} catch (BusinessException e) {
									log.warn(e.getMessage());
								}
								break;
							case 3:
								log.info("Now you will see products based on selected Category");
								log.info("Select Category : ");
								try {
									String category = scanner.nextLine();
									List<Product> productList = productMethodService.getProductByCategory(category);
									if (productList != null && productList.size() > 0) {
										log.info("Total there are " + productList.size()
												+ " number of products in this category " + category.toUpperCase()
												+ " getting the products");
										for (Product product : productList) {
											log.info(product);
										}
									}
									int orderchoicebycategory=0;
									switch (orderchoicebycategory) {
									case 1:
										
										int quantity=0;
										try {
											
											int customerId = CustomerDAOImpl.ad;
										log.info("Add Quantity : ");
										quantity = Integer.parseInt(scanner.nextLine());
										CartService cartService = new CartServiceImpl();
								
										int c = cartService.addProductToCartByCategory(category,customerId);
										
										if(c==1) {
											log.info("placed into cart");
										}
										
										
										
									}catch (NumberFormatException e) {
										log.warn(e.getMessage());
										log.info("Product Id should be digit only... Try Again");
									}
									
										
										break;
									case 2:
										break;
									default:
										log.info(
												"Invalid Search Option... Choice should be only number between"
												+ " 1-2 only. Kindly Retry");
									} while (orderchoicebycategory != 2);

								} catch (NumberFormatException e) {
									log.warn(e.getMessage());

									log.info("Product name should be text only... Try Again");
								} catch (BusinessException e) {
									log.warn(e.getMessage());
								}
								

								break;

							
							case 4:
								log.info("logOut");
								System.exit(1);
								break;
							default:
								log.info(
										"Invalid Product Search Option... Choice should be only number between 1-6 only. Kindly Retry ");

							}

						} while (productsearchchoice != 6);

						break;
					case 2:
						log.info("Viewing Cart. -");
						Cart cart =new Cart();
						try {
							CartService cartService = new CartServiceImpl();
							List<Cart> cartList = cartService.viewCart(cart);
							
							double totalCartPrice=0;
							if(cartList.size()>0) {
							
							for(Cart cart1:cartList) {
								log.info(cart1.getProduct().getProduct_name()+"  |  "+cart1.getProduct().getProduct_price());
								totalCartPrice = cartList.stream().mapToDouble(c->c.getProduct().getProduct_price()).sum();
							}
							log.info("Total Cart Price : "+totalCartPrice);
							int ordernow=0;
							do {
								log.info("Do you Wish to Place Order for the above products in the card");
								log.info("1] Yes");
								log.info("2] No");
								try {
									ordernow = Integer.parseInt(scanner.nextLine());
								}catch (NumberFormatException e) {
									log.info(e.getMessage());
								}
								
								switch(ordernow) {
								case 1:
									 Order order =new Order();
									OrderService orderservice = new OrderServiceImpl();
									for(Cart cart1:cartList) {
										log.info(cart1.getProduct().getProduct_name()+ "   "+ cart1.getProduct().getProduct_price()+" is ordered");
										int a=0;
										a=orderservice.placeOrder(cart1);
										if(a==1) {
											log.info("Order Placed" +" with orderId "+order.getOrderId() );
										}
									}
									break;
								case 2:
									log.info("Returning Back");
									break;
								}
								
								
							}while(ordernow!=2);
							}
							else {
								log.info("Your cart is Empty");
								log.info("Start Shopping");
							}
							
							

						} catch (BusinessException e) {
							log.warn(e.getMessage());
						}
						
						
						
						break;
					case 3:
						log.info("Viewing orders : ");
						
						List<Order> orderList;
						OrderService orderService = new OrderServiceImpl();
						try {
							orderList = orderService.getOrders(CustomerDAOImpl.ad);

							log.info("You Have ordered " + orderList.size() + " Orders and details are below --> \n");
							//orderList.stream().forEach(System.out::println);
							for(Order order : orderList) {
								log.info(order.getOrderId() +"  |   "+ order.getProduct().getProduct_name() +"   |   "+
										order.getProduct().getProduct_price());
							}

						} catch (BusinessException e) {
							log.info(e.getMessage());
						}
						int statuschoice=0;
						do {
							
							log.info("--------------------------");
							log.info("1)Update  Status");
							log.info("2)Go to previous menu");
							try {
								statuschoice = Integer.parseInt(scanner.nextLine());

								switch (statuschoice) {
								case 1:
									log.info("Enter Order id to update status");
									int orderId = Integer.parseInt(scanner.nextLine());
									
									log.info("Updating Customer Status to : Received");
									String status = "Received";
									if (orderService.updateOrderStatus(orderId, status) == 1) {
										log.info("Update Status is Done");
										log.info("Products of particular orderId: "+orderId+ "received");
									break;}
								case 2:
									log.info("Returning to previous menu ");
									break;
								default:
									log.warn("Please enter valid choice (1-2)\n");
								}
							} catch (NumberFormatException e) {
								log.info("Not Valid choice");
							} catch (BusinessException e) {
								log.info(e.getMessage());
							}
						} while (statuschoice != 2);
						
						break;
					default:
						log.info("");
						break;
					}

				} while (loginchoice != 3);
				break;

			case 2:
				log.info("\n Hi! Employee Welcome");
				log.info("Enter your Employee email : ");
				String employee_email = scanner.nextLine();
				log.info("Enter your Employee Password");
				String employee_password = scanner.nextLine();				
				
				
				EmployeeService employeeService = new EmployeeServiceImpl();
				try {
					
					Employee employee = employeeService.isValidEmployee(employee_email,employee_password);

					if (employee != null) {
						log.info("Login Successfull. :)");
						log.info("Welcome " + employee.getEmployee_mail() + ":) ");
					}
				} catch (BusinessException e) {
					log.warn(e.getMessage());
				}

				log.info("Login Successfull. again :)");
				int employeechoice=0;
				do {
					log.info("What do you wish to do my lord :");
					log.info("1] Add Products To Our Shopping Portal");
					log.info("2] Want to Search Customers ? ");
					log.info("3] Update Order Status");
					log.info("4] LogOut");
					
					
					try {
						employeechoice = Integer.parseInt(scanner.nextLine());
					} catch (NumberFormatException e) {
						log.warn(e.getMessage());
					}
					switch(employeechoice) {
					case 1:
						log.info("Welcome to Product Addition Section");
						log.info("Give Valid Entries : ");
						log.info("Enter Product Name : ");
						String productname = scanner.nextLine();
						log.info("Enter Product Price : ");
						double price= Double.parseDouble(scanner.nextLine());
						log.info("Enter Product Quantity to be added : ");
						int quantity= Integer.parseInt(scanner.nextLine());
						log.info("Enter Product Category : ");
						String category= scanner.nextLine();
						try {
							List<Product> productList = employeeService.addProduct(productname, price,quantity, category);
							
							for (Product product : productList) {
								log.info(product);

							}
						} catch (BusinessException e) {
							log.warn(e.getMessage());
						}
						
						break;
					case 2:
						
						
						int searchchoice=0;
						do {
							log.info("1] Search based on Id");
							log.info("2] Search based on First Name");
							
							log.info("3] Search based on email");
							log.info("4] Search based on OrderId");
							log.info("5] wish to go back --> ");
							
							try {
								searchchoice = Integer.parseInt(scanner.nextLine());
							} catch (NumberFormatException e) {
								log.warn(e.getMessage());
							}
							switch(searchchoice) {
							case 1:
								log.info("Enter customer id to get customer details");
								try {
								int id=Integer.parseInt(scanner.nextLine());
								Customer customer = new Customer();
								customer =employeeService.getCustomerById(id);
								if(customer!=null) {
									log.info("Customer with id "+id+" found successfully... Below is the details");
									log.info(customer);
								}
								
								}catch(NumberFormatException e) {
									log.warn("Customer Id should be digit only... Try Again");
								} catch (BusinessException e) {
									log.warn(e.getMessage());
								}
								break;
							case 2:
								log.info("Enter Customer First Name to get customer details");
								try {
								String firstName=scanner.nextLine();
								Customer customer = new Customer();
								customer =employeeService.getCustomerByFirstName(firstName);
								if(customer!=null) {
									log.info("Customer with First Name "+firstName+" found successfully... Below is the details");
									log.info(customer);
								}
								
								}catch(NumberFormatException e) {
									log.warn("Customer Id should be digit only... Try Again");
								} catch (BusinessException e) {
									log.warn(e.getMessage());
								}
								
								break;
							case 3:
								log.info("Enter Customer email to get customer details");
								try {
								String email=scanner.nextLine();
								Customer customer = new Customer();
								customer =employeeService.getCustomerByEmail(email);
								if(customer!=null) {
									log.info("Customer with email "+email+" found successfully... Below is the details");
									log.info(customer);
								}
								
								}catch(NumberFormatException e) {
									log.warn("Customer email not proper... Try Again");
								} catch (BusinessException e) {
									log.warn(e.getMessage());
								}
								break;
							case 4:
								log.info("Enter Customer orderId to get customer details");
								try {
								int orderid=Integer.parseInt(scanner.nextLine());
								Customer customer = new Customer();
								customer =employeeService.getCustomerByOrderId(orderid);
								if(customer!=null) {
									log.info("Customer with email "+orderid+" found successfully... Below is the details");
									log.info(customer);
								}
								
								}catch(NumberFormatException e) {
									log.warn("Customer orderId not proper... Try Again");
								} catch (BusinessException e) {
									log.warn(e.getMessage());
								}
								break;
					
							}
						}while(searchchoice!=5);
						
						
						
						break;
					default:
						log.info("Invalid Search Option... Choice should be only number between 1-2 only. Kindly Retry");
					}
					
				}while(employeechoice!=3);
				
				
				
				
				
				
				break;
			case 3:
				log.info("Begin with us...");
				String firstName;
				String lastName;
				String email;
				String password;
				String confirmpassword;
				log.info("Enter First Name : ");
				firstName = scanner.nextLine();
				log.info("Enter Last Name : ");
				lastName = scanner.nextLine();
				log.info("Enter your email : ");
				email = scanner.nextLine();
				log.info("Enter Password : ");
				password = scanner.nextLine();
				log.info("confirm password : ");
				confirmpassword = scanner.nextLine();
				if (password == confirmpassword) {
					log.info("Successfully registered");
				}

				CustomerService customerService = new CustomerServiceImpl();
				try {
					List<Customer> customerList = customerService.addingConsumer(firstName, lastName, email, password);
					log.info("Customer registered Successfully");
					for (Customer customer : customerList) {
						log.info(customer);

					}
				} catch (BusinessException e) {
					log.warn(e.getMessage());
				}

				break;
			case 4:
				log.info("\n\n Pushing you out soon");

				break;

			}

		} while (mainchoice != 4);
		scanner.close();
	}

}
