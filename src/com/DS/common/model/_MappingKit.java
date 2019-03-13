package com.DS.common.model;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;

/**
 * Generated by JFinal, do not modify this file.
 * <pre>
 * Example:
 * public void configPlugin(Plugins me) {
 *     ActiveRecordPlugin arp = new ActiveRecordPlugin(...);
 *     _MappingKit.mapping(arp);
 *     me.add(arp);
 * }
 * </pre>
 */
public class _MappingKit {
	
	public static void mapping(ActiveRecordPlugin arp) {
		arp.addMapping("menu", "id", Menu.class);
		arp.addMapping("notification", "id", Notification.class);
		arp.addMapping("project", "id", Project.class);
		arp.addMapping("project_tree", "id", ProjectTree.class);
		arp.addMapping("remind", "id", Remind.class);
		arp.addMapping("user", "id", User.class);
	}
}

