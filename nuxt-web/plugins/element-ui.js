import Vue from 'vue'
import * as ElementUI from 'element-ui'

let elementNames = [
  'Pagination',
  // 'Dialog',
  // 'Autocomplete',
  // 'Dropdown',
  // 'DropdownMenu',
  // 'DropdownItem',
  'Menu',
  'Submenu',
  'MenuItem',
  // 'MenuItemGroup',
  // 'Input',
  // 'InputNumber',
  // 'Radio',
  // 'RadioGroup',
  // 'RadioButton',
  // 'Checkbox',
  // 'CheckboxGroup',
  // 'Switch',
  // 'Select',
  // 'Option',
  // 'OptionGroup',
  // 'Button',
  // 'ButtonGroup',
  // 'Table',
  // 'TableColumn',
  // 'DatePicker',
  // 'TimeSelect',
  // 'TimePicker',
  // 'Popover',
  'Tooltip',
  // 'Breadcrumb',
  // 'BreadcrumbItem',
  // 'Form',
  // 'FormItem',
  // 'Tabs',
  // 'TabPane',
  // 'Tag',
  // 'Tree',
  // 'Alert',
  // 'Slider',
  // 'Icon',
  'Row',
  'Col',
  // 'Upload',
  // 'Progress',
  // 'Spinner',
  // 'Badge',
  // 'Card',
  // 'Rate',
  'Steps',
  'Step',
  'Carousel',
  // 'Scrollbar',
  'CarouselItem',
  // 'Collapse',
  // 'CollapseItem',
  // 'Cascader',
  // 'ColorPicker'
]

elementNames.forEach((name, index) => {
  Vue.use(ElementUI[name])
})

Vue.use(ElementUI.Loading.directive)

Vue.prototype.$loading = ElementUI.Loading.service
Vue.prototype.$msgbox = ElementUI.MessageBox
Vue.prototype.$alert = ElementUI.MessageBox.alert
Vue.prototype.$confirm = ElementUI.MessageBox.confirm
Vue.prototype.$prompt = ElementUI.MessageBox.prompt
Vue.prototype.$notify = ElementUI.Notification
Vue.prototype.$message = ElementUI.Message
