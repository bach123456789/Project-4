package com.javaweb.controller.admin;

import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.CustomerService;
import com.javaweb.service.impl.UserService;
import com.javaweb.utils.DisplayTagUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CustomerController
{
    @Autowired
    CustomerService customerService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/admin/customer-list", method = RequestMethod.GET)
    public ModelAndView buildingList(@ModelAttribute CustomerSearchRequest customerSearchRequest, HttpServletRequest request)
    {
        ModelAndView mav = new ModelAndView("admin/customer/list");
        mav.addObject("modelSearch", customerSearchRequest);

        if(SecurityUtils.getAuthorities().contains("ROLE_STAFF"))
        {
            Long staffId = SecurityUtils.getPrincipal().getId();
            customerSearchRequest.setStaffId(staffId);
            mav.addObject("customerList", customerService.findAll(customerSearchRequest, PageRequest.of(customerSearchRequest.getPage() - 1, customerSearchRequest.getMaxPageItems())));
        }
        else mav.addObject("customerList", customerService.findAll(customerSearchRequest, PageRequest.of(customerSearchRequest.getPage() - 1, customerSearchRequest.getMaxPageItems())));

        List<CustomerSearchResponse> res = customerService.findAll(customerSearchRequest, PageRequest.of(customerSearchRequest.getPage() - 1, customerSearchRequest.getMaxPageItems()));
        CustomerSearchResponse customerSearchResponse = new CustomerSearchResponse();
        DisplayTagUtils.of(request, customerSearchResponse);
        customerSearchResponse.setListResult(res);
        customerSearchResponse.setTotalItems(customerService.countTotalItem(res));

        mav.addObject("customerList", customerSearchResponse);
        mav.addObject("listStaffs", userService.getStaffs());
        return mav;
    }

    @RequestMapping(value = "/admin/customer-edit", method = RequestMethod.GET)
    public ModelAndView buildingEdit(@ModelAttribute("customerEdit") CustomerDTO customerDTO, HttpServletRequest request)
    {
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        return mav;
    }

    @RequestMapping(value = "/admin/customer-edit-{id}", method = RequestMethod.GET)
    public ModelAndView buildingEdit(@PathVariable("id") Long id, HttpServletRequest request)
    {
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        CustomerDTO customerDTO = customerService.findById(id);
        mav.addObject("customerEdit", customerDTO);
        return mav;
    }
}
