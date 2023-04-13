using BookStore.Models;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Web;

namespace BookStore.Controllers
{
    public class CartController : Controller
    {
        public IActionResult ViewCart()
        {            
            Session session = appDbContext.Sessions.FirstOrDefault(x => x.Id == HttpContext.Request.Cookies["sessionId"]);
            if (session == null)
            {
                return RedirectToAction("index", "home");
            }
            Cart cart = appDbContext.Carts.FirstOrDefault(x => x.CustomerId == session.Customer.Id && x.IsCheckOut == false);
            if (cart == null)
            {
                return RedirectToAction("index", "home");
            }
            List<CartItem> itemincart = appDbContext.CartItems.Where(x => x.CartId == cart.Id).ToList();
            ViewData["itemincart"] = itemincart;
            return View();
        }

        private readonly AppDbContext appDbContext;

        public CartController(AppDbContext appDbContext)
        {
            this.appDbContext = appDbContext;
        }

        public IActionResult AddCart(int id)
        {
            Book book = appDbContext.Books.FirstOrDefault(x => x.Id == id);

            Session session = appDbContext.Sessions.FirstOrDefault(x => x.Id == HttpContext.Request.Cookies["sessionId"]);
            if (session == null)
            {
                return RedirectToAction("login", "account");
            }

            else
            {
                Cart cart = appDbContext.Carts.FirstOrDefault(x => x.CustomerId == session.Customer.Id && x.IsCheckOut == false);

                if (cart == null)
                {
                    cart = new Cart(session.Customer.Id);
                    appDbContext.Carts.Add(cart);
                };
                CartItem cartItem = new CartItem();
                cartItem.Book = book;
                cartItem.CartId = cart.Id;
                cartItem.Quantity = 1;
                cartItem.BookId = book.Id;
                cartItem.Cart = cart;
                if(appDbContext.CartItems.FirstOrDefault(x => x.BookId==cartItem.BookId && x.CartId ==cartItem.CartId)==null)
                {          
                    appDbContext.CartItems.Add(cartItem);
                    appDbContext.SaveChanges();
                }
                else
                {
                    appDbContext.CartItems.FirstOrDefault(x => x.BookId == cartItem.BookId && x.CartId == cartItem.CartId).Quantity += 1;
                    appDbContext.SaveChanges();
                }


                cart.Quantity += 1;
                appDbContext.SaveChanges();

                List<CartItem> itemincart = appDbContext.CartItems.Where(x => x.CartId == cart.Id).ToList();

                return RedirectToAction("index", "home");

            }
            
        }

        public IActionResult Remove(int cartid, int pdtid)
        {
            List<CartItem> itemtoremove = appDbContext.CartItems.Where(x => x.BookId == pdtid && x.CartId == cartid).ToList();
            List<Cart> carttoremove = appDbContext.Carts.Where(x => x.Id == cartid).ToList();
            int num = appDbContext.Carts.FirstOrDefault(x => x.Id == cartid).Quantity - itemtoremove[0].Quantity;
            if (num <= 0)
            {
                foreach (Cart ctr in carttoremove)
                {
                    appDbContext.Carts.Remove(ctr);
                    appDbContext.SaveChanges();
                }
            }
            else
            {
                foreach (CartItem cartit in itemtoremove)
                {
                    appDbContext.Carts.FirstOrDefault(x => x.Id == cartid).Quantity -= itemtoremove[0].Quantity;
                    appDbContext.CartItems.Remove(cartit);
                    appDbContext.SaveChanges();
                }
            }
            return RedirectToAction("ViewCart", "Cart");
        }

        public IActionResult Add(int cartid, int pdtid)
        {
            appDbContext.Carts.FirstOrDefault(x => x.Id == cartid).Quantity += 1;
            appDbContext.CartItems.FirstOrDefault(x => x.BookId == pdtid && x.CartId == cartid).Quantity += 1;
            appDbContext.SaveChanges();
            return RedirectToAction("ViewCart", "Cart");
        }

        public IActionResult Minus(int cartid, int pdtid)
        {
            appDbContext.Carts.FirstOrDefault(x => x.Id == cartid).Quantity -= 1;
            appDbContext.CartItems.FirstOrDefault(x => x.BookId == pdtid && x.CartId == cartid).Quantity -= 1;
            int num1 = appDbContext.Carts.FirstOrDefault(x => x.Id == cartid).Quantity;
            int num2 = appDbContext.CartItems.FirstOrDefault(x => x.BookId == pdtid && x.CartId == cartid).Quantity;
            appDbContext.SaveChanges();
            List<Cart> carttoremove = appDbContext.Carts.Where(x => x.Id == cartid).ToList();
            if (num1 <= 0)
            {
                foreach (Cart ctr in carttoremove)
                {
                    appDbContext.Carts.Remove(ctr);
                    appDbContext.SaveChanges();
                }
                return RedirectToAction("ViewCart", "Cart");
            }
            else
            {
                if (num2 <= 0)
                {
                    return RedirectToAction("Remove", "Cart", new { cartid = cartid, pdtid = pdtid });
                }
                else
                {
                    return RedirectToAction("ViewCart", "Cart");
                }
            }
        }
    }
}

