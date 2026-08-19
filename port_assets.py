#!/usr/bin/env python3
"""
Asset porting script for Slaughter & Hide mod.
Copies assets from Butchery decompiled assets (temp/butchery-assets) to the port
(src/main/resources), applying:
- Namespace remap: butchery: -> slaughter_hide:
- Drained blockstate stage fix (keep only 0,6,7,8)
- polar_bear naming normalization (polarbear_* -> polar_bear_*)
"""

import json
import os
import re
import shutil
from pathlib import Path

SRC_BASE = Path("G:/Proyectos/Mods_Minecraft/slaughter_hide/neoforge/26.2/temp/butchery-assets")
DST_BASE = Path("G:/Proyectos/Mods_Minecraft/slaughter_hide/neoforge/26.2/src/main/resources")

# Mobs to port in this batch (the 9 that fit the generic 5-stage system)
MOBS = [
    ("camel", "camel", True),           # (mob_id, original_prefix, has_skeleton)
    ("donkey", "donkey", True),
    ("mule", "mule", True),
    ("ocelot", "ocelot", True),
    ("panda", "panda", True),
    ("polar_bear", "polar_bear", True),  # special: original uses mixed polar_bear_ / polarbear_
    ("hoglin", "hoglin", True),
    ("zoglin", "zoglin", False),        # NO skeleton
    ("dolphin", "dolphin", True),
    ("bat", "bat", True),               # standard
    ("silverfish", "silverfish", False), # NO skeleton
]

# Special naming rules for polar_bear (original -> target)
POLAR_BEAR_RENAMES = {
    "polarbear_head_mount": "polar_bear_head_mount",
    "polarbear_skeleton": "polar_bear_skeleton",
    "polarbear_rug": "polar_bear_rug",
    "polarbear_carcass": "polar_bear_carcass",  # custom models
    "polarbear_corpse_bounding": "polar_bear_corpse_bounding",
    "polarbear_hanging_bounding": "polar_bear_hanging_bounding",
    "polarbear_head": "polar_bear_head",
    "polarbear_skeleton_hanging": "polar_bear_skeleton_hanging",
    "polarbear_carcass_hanging": "polar_bear_carcass_hanging",
    "polarbear_carcass_drained_hanging": "polar_bear_carcass_drained_hanging",
    "polarbear_carcass_headless_hanging": "polar_bear_carcass_headless_hanging",
    "polarbear_carcass_headless_skinned_hanging": "polar_bear_carcass_headless_skinned_hanging",
    "polarbear_cut_1_carcass": "polar_bear_cut_1_carcass",
    "polarbear_cut_2_carcass": "polar_bear_cut_2_carcass",
    "polarbear_drained_carcass": "polar_bear_drained_carcass",
    "polarbear_carcass_cut_1_hanging": "polar_bear_carcass_cut_1_hanging",
    "polarbear_carcass_cut_2_hanging": "polar_bear_carcass_cut_2_hanging",
}

# Special for panda: original uses hanging_panda_* for some custom models
PANDA_HANGING_RENAMES = {
    "hanging_panda_carcass": "panda_carcass_hanging",
    "hanging_panda_carcass_bounding": "panda_carcass_hanging_bounding",
    "hanging_panda_cut_2_carcass": "panda_carcass_cut_2_hanging",
    "hanging_panda_drained_carcass": "panda_carcass_drained_hanging",
    "hanging_panda_headless_carcass": "panda_carcass_headless_hanging",
    "hanging_panda_headless_cut_1_carcass": "panda_carcass_headless_cut_1_hanging",
    "hanging_panda_headless_cut_2_carcass": "panda_carcass_headless_cut_2_hanging",
    "hanging_panda_headless_skinned_carcass": "panda_carcass_headless_skinned_hanging",
    "hanging_panda_skinned_carcass": "panda_carcass_skinned_hanging",
    "hanging_panda_rug": "panda_rug_hanging",
}

def remap_namespace(text):
    """Replace butchery: with slaughter_hide: in JSON strings."""
    # Only replace in quoted strings that look like resource locations
    # Use regex to replace butchery: followed by valid path chars
    return re.sub(r'"(butchery:)', r'"slaughter_hide:', text)

def copy_file_with_remap(src, dst, namespace_remap=True):
    """Copy file, optionally remapping butchery: -> slaughter_hide: in content."""
    dst.parent.mkdir(parents=True, exist_ok=True)
    if namespace_remap and src.suffix == '.json':
        content = src.read_text(encoding='utf-8')
        content = remap_namespace(content)
        dst.write_text(content, encoding='utf-8')
    else:
        shutil.copy2(src, dst)

def fix_drained_blockstate(src_path, dst_path, mob_id):
    """Rewrite drained blockstate to keep only stages 0,6,7,8 with model mapping."""
    dst_path.parent.mkdir(parents=True, exist_ok=True)
    data = json.loads(src_path.read_text(encoding='utf-8'))
    
    new_variants = {}
    for key, value in data.get("variants", {}).items():
        # Parse facing and blockstate
        if "facing=" in key and "blockstate=" in key:
            parts = key.split(",")
            facing = parts[0].split("=")[1]
            blockstate = int(parts[1].split("=")[1])
            
            # Keep only stages 0,6,7,8
            if blockstate not in (0, 6, 7, 8):
                continue
            
            # Remap model names
            model = value.get("model", "")
            if blockstate == 0:
                # Base model
                new_model = model.replace(f"butchery:block/drained_{mob_id}_carcass_", f"slaughter_hide:block/drained_{mob_id}_carcass")
                new_model = new_model.replace("butchery:block/", "slaughter_hide:block/")
            elif blockstate == 6:
                # Map to _5
                new_model = model.replace(f"butchery:block/drained_{mob_id}_carcass_0", f"slaughter_hide:block/drained_{mob_id}_carcass_5")
                new_model = new_model.replace("butchery:block/", "slaughter_hide:block/")
            elif blockstate == 7:
                # Map to _6
                new_model = model.replace(f"butchery:block/drained_{mob_id}_carcass_1", f"slaughter_hide:block/drained_{mob_id}_carcass_6")
                new_model = new_model.replace("butchery:block/", "slaughter_hide:block/")
            elif blockstate == 8:
                # Map to _7
                new_model = model.replace(f"butchery:block/drained_{mob_id}_carcass_2", f"slaughter_hide:block/drained_{mob_id}_carcass_7")
                new_model = new_model.replace("butchery:block/", "slaughter_hide:block/")
            else:
                new_model = model.replace("butchery:block/", "slaughter_hide:block/")
            
            new_value = {"model": new_model}
            if "y" in value:
                new_value["y"] = value["y"]
            new_variants[f"facing={facing},blockstate={blockstate}"] = new_value
    
    # Sort variants for consistent output
    sorted_variants = dict(sorted(new_variants.items()))
    
    result = {"variants": sorted_variants}
    dst_path.write_text(json.dumps(result, indent=2) + "\n", encoding='utf-8')

def fix_fresh_blockstate(src_path, dst_path, mob_id):
    """Rewrite fresh carcass blockstate with namespace remap only."""
    dst_path.parent.mkdir(parents=True, exist_ok=True)
    data = json.loads(src_path.read_text(encoding='utf-8'))
    
    new_variants = {}
    for key, value in data.get("variants", {}).items():
        model = value.get("model", "")
        new_model = model.replace("butchery:block/", "slaughter_hide:block/")
        new_value = {"model": new_model}
        if "y" in value:
            new_value["y"] = value["y"]
        new_variants[key] = new_value
    
    result = {"variants": dict(sorted(new_variants.items()))}
    dst_path.write_text(json.dumps(result, indent=2) + "\n", encoding='utf-8')

def apply_polar_bear_rename(name):
    """Apply polar_bear special renames to a filename stem."""
    for orig, target in POLAR_BEAR_RENAMES.items():
        if name == orig or name.startswith(orig + "_") or name.startswith(orig + "."):
            return name.replace(orig, target, 1)
    return name

def apply_panda_rename(name):
    """Apply panda hanging renames to a filename stem."""
    for orig, target in PANDA_HANGING_RENAMES.items():
        if name.startswith(orig):
            return name.replace(orig, target, 1)
    return name

def find_matching_files(src_dir, prefix, suffix=""):
    """Find all files in src_dir starting with prefix."""
    return sorted(src_dir.glob(f"{prefix}*.json"))

def find_matching_files_exact(src_dir, prefix, suffix=""):
    """Find files where stem starts with prefix + '_' or equals prefix."""
    results = []
    for f in src_dir.glob("*.json"):
        stem = f.stem
        if stem == prefix or stem.startswith(prefix + "_"):
            results.append(f)
    return sorted(results)

def port_mob(mob_id, orig_prefix, has_skeleton):
    print(f"\n=== Porting {mob_id} (prefix: {orig_prefix}) ===")
    
    # 1. BLOCKSTATES
    print("  Blockstates...")
    blockstates_src = SRC_BASE / "assets" / "butchery" / "blockstates"
    blockstates_dst = DST_BASE / "assets" / "slaughter_hide" / "blockstates"
    
    # Fresh carcass
    fresh_src = blockstates_src / f"{orig_prefix}_carcass.json"
    if fresh_src.exists():
        fresh_dst = blockstates_dst / f"{mob_id}_carcass.json"
        fix_fresh_blockstate(fresh_src, fresh_dst, mob_id)
        print(f"    {fresh_dst.name}")
    
    # Drained carcass
    drained_src = blockstates_src / f"drained_{orig_prefix}_carcass.json"
    if drained_src.exists():
        drained_dst = blockstates_dst / f"drained_{mob_id}_carcass.json"
        fix_drained_blockstate(drained_src, drained_dst, mob_id)
        print(f"    {drained_dst.name}")
    
    # Head
    head_src = blockstates_src / f"{orig_prefix}_head.json"
    if head_src.exists():
        head_dst = blockstates_dst / f"{mob_id}_head.json"
        copy_file_with_remap(head_src, head_dst)
        print(f"    {head_dst.name}")
    
    # Head mount
    head_mount_src = blockstates_src / f"{orig_prefix}_head_mount.json"
    if not head_mount_src.exists() and mob_id == "polar_bear":
        head_mount_src = blockstates_src / "polarbear_head_mount.json"
    if head_mount_src.exists():
        head_mount_dst = blockstates_dst / f"{mob_id}_head_mount.json"
        copy_file_with_remap(head_mount_src, head_mount_dst)
        print(f"    {head_mount_dst.name}")
    
    # Skeleton (only if has_skeleton)
    if has_skeleton:
        skeleton_src = blockstates_src / f"{orig_prefix}_skeleton.json"
        if not skeleton_src.exists() and mob_id == "polar_bear":
            skeleton_src = blockstates_src / "polarbear_skeleton.json"
        if skeleton_src.exists():
            skeleton_dst = blockstates_dst / f"{mob_id}_skeleton.json"
            copy_file_with_remap(skeleton_src, skeleton_dst)
            print(f"    {skeleton_dst.name}")
    
    # 2. MODELS/BLOCK
    print("  Models/block...")
    models_block_src = SRC_BASE / "assets" / "butchery" / "models" / "block"
    models_block_dst = DST_BASE / "assets" / "slaughter_hide" / "models" / "block"
    
    for src_file in find_matching_files_exact(models_block_src, orig_prefix):
        stem = src_file.stem
        dst_name = f"{mob_id}_{stem[len(orig_prefix)+1:]}.json"
        if mob_id == "polar_bear":
            dst_name = apply_polar_bear_rename(dst_name)
        dst_file = models_block_dst / dst_name
        copy_file_with_remap(src_file, dst_file)
        print(f"    {dst_name}")
    
    # Also copy drained models
    for src_file in find_matching_files_exact(models_block_src, f"drained_{orig_prefix}"):
        stem = src_file.stem
        dst_name = f"drained_{mob_id}_{stem[len(f'drained_{orig_prefix}')+1:]}.json"
        if mob_id == "polar_bear":
            dst_name = apply_polar_bear_rename(dst_name)
        dst_file = models_block_dst / dst_name
        copy_file_with_remap(src_file, dst_file)
        print(f"    {dst_name}")
    
    # 3. MODELS/CUSTOM
    print("  Models/custom...")
    models_custom_src = SRC_BASE / "assets" / "butchery" / "models" / "custom"
    models_custom_dst = DST_BASE / "assets" / "slaughter_hide" / "models" / "custom"
    
    # For polar_bear, original uses "polarbear_" prefix
    custom_prefix = orig_prefix
    if mob_id == "polar_bear":
        custom_prefix = "polarbear"
    
    for src_file in find_matching_files_exact(models_custom_src, custom_prefix):
        stem = src_file.stem
        dst_name = f"{mob_id}_{stem[len(custom_prefix)+1:]}.json"
        if mob_id == "polar_bear":
            dst_name = apply_polar_bear_rename(dst_name)
        elif mob_id == "panda":
            dst_name = apply_panda_rename(dst_name)
        dst_file = models_custom_dst / dst_name
        copy_file_with_remap(src_file, dst_file)
        print(f"    {dst_name}")
    
    # Special: camel has "camels_head.json" (plural)
    if mob_id == "camel":
        src_file = models_custom_src / "camels_head.json"
        if src_file.exists():
            dst_file = models_custom_dst / f"{mob_id}_head.json"
            copy_file_with_remap(src_file, dst_file)
            print(f"    camel_head.json (from camels_head)")
    
    # 4. MODELS/ITEM
    print("  Models/item...")
    models_item_src = SRC_BASE / "assets" / "butchery" / "models" / "item"
    models_item_dst = DST_BASE / "assets" / "slaughter_hide" / "models" / "item"
    
    # Copy carcass/head/mount/skeleton/drained item models
    item_patterns = [
        f"{orig_prefix}_carcass.json",
        f"drained_{orig_prefix}_carcass.json",
        f"{orig_prefix}_head.json",
        f"{orig_prefix}_head_mount.json",
        f"{orig_prefix}_skeleton.json",
        f"{orig_prefix}_skin.json",
    ]
    
    for pattern in item_patterns:
        src_file = models_item_src / pattern
        if src_file.exists():
            dst_name = pattern.replace(orig_prefix, mob_id)
            if mob_id == "polar_bear" and "polarbear" in dst_name:
                dst_name = dst_name.replace("polarbear", "polar_bear")
            dst_file = models_item_dst / dst_name
            copy_file_with_remap(src_file, dst_file)
            print(f"    {dst_name}")
        # Also check for polar_bear polarbear prefix variants
        if mob_id == "polar_bear":
            alt_pattern = pattern.replace("polar_bear", "polarbear")
            src_file_alt = models_item_src / alt_pattern
            if src_file_alt.exists() and not src_file.exists():
                dst_name = pattern.replace("polar_bear", "polar_bear").replace("polarbear", "polar_bear")
                dst_file = models_item_dst / dst_name
                copy_file_with_remap(src_file_alt, dst_file)
                print(f"    {dst_name} (from polarbear)")
    
    # 5. TEXTURES - BLOCK
    print("  Textures/block...")
    tex_block_src = SRC_BASE / "assets" / "butchery" / "textures" / "block"
    tex_block_dst = DST_BASE / "assets" / "slaughter_hide" / "textures" / "block"
    
    # Known texture patterns for these mobs
    tex_patterns = [
        f"{orig_prefix}.png",
        f"{orig_prefix}_skinned.png",
        f"{orig_prefix}_skin.png",
        f"{orig_prefix}_skeleton.png",
    ]
    
    if mob_id == "polar_bear":
        tex_patterns = [
            "polarbear.png",
            "polarbear_skinned.png",
            "polarbear_skin.png",
            "polarbear_skeleton.png",
        ]
    elif mob_id == "panda":
        tex_patterns = [
            "panda_hanging.png",  # panda uses panda_hanging.png
            "skinned_panda.png",  # and skinned_panda.png
            "panda_skin.png",
            "panda_skeleton.png",
            "panda_rug.png",
        ]
    elif mob_id == "camel":
        tex_patterns = [
            "camel.png",
            "camel_skin.png",
            "camel_skinned.png",
        ]
    elif mob_id == "hoglin":
        tex_patterns = [
            "hoglin.png",
            "hoglin_drained.png",  # hoglin uses hoglin_drained.png
            "hoglin_skin.png",
            "hoglin_skinned.png",
            "hoglin_skeleton.png",
        ]
    elif mob_id == "zoglin":
        tex_patterns = [
            "zoglin.png",
            "zoglin_skin.png",
            "zoglin_skeleton.png",
            "skinned_zoglin.png",
        ]
    elif mob_id == "dolphin":
        tex_patterns = [
            "dolphin.png",
            "dolphin_skeleton.png",
            "dolphin_skin.png",
            "dolphin_skinned.png",
        ]
    elif mob_id == "donkey":
        tex_patterns = [
            "donkey_placed.png",  # donkey uses donkey_placed.png
            "donkey_skin.png",
            "donkey_skinned.png",
        ]
    elif mob_id == "mule":
        tex_patterns = [
            "mule.png",
            "mule_skin.png",
        ]
    elif mob_id == "ocelot":
        tex_patterns = [
            "ocelot.png",
            "ocelot_2.png",  # ocelot has ocelot_2.png
            "ocelot_skin.png",
            "ocelot_skinned.png",
        ]
    
    for pattern in tex_patterns:
        src_file = tex_block_src / pattern
        if src_file.exists():
            dst_name = pattern
            if mob_id == "polar_bear":
                dst_name = dst_name.replace("polarbear", "polar_bear")
            elif mob_id == "panda":
                pass  # keep as-is
            elif mob_id == "hoglin":
                pass
            elif mob_id == "zoglin":
                pass
            elif mob_id == "dolphin":
                pass
            elif mob_id == "donkey":
                if pattern == "donkey_placed.png":
                    dst_name = f"{mob_id}.png"
            elif mob_id == "ocelot":
                if pattern == "ocelot_2.png":
                    dst_name = f"{mob_id}_2.png"
            dst_file = tex_block_dst / dst_name
            shutil.copy2(src_file, dst_file)
            print(f"    {dst_name}")
    
    # 6. TEXTURES - ITEM (skins and meats)
    print("  Textures/item...")
    tex_item_src = SRC_BASE / "assets" / "butchery" / "textures" / "item"
    tex_item_dst = DST_BASE / "assets" / "slaughter_hide" / "textures" / "item"
    
    item_tex_patterns = [
        f"{orig_prefix}_skin.png",
        f"{orig_prefix}_fur.png",
        f"raw_{orig_prefix}_meat.png",
        f"raw_{orig_prefix}_steak.png",
        f"raw_{orig_prefix}_chunk.png",
        f"cooked_{orig_prefix}_meat.png",
    ]
    
    # Specific mappings per mob
    if mob_id == "polar_bear":
        item_tex_patterns = ["polarbear_fur.png", "raw_polar_bear_meat.png"]
    elif mob_id == "panda":
        item_tex_patterns = ["panda_skin.png", "raw_panda_meat.png"]
    elif mob_id == "camel":
        item_tex_patterns = ["camel_fur.png", "raw_camel_meat.png", "cooked_camel_meat.png"]
    elif mob_id == "donkey":
        item_tex_patterns = ["donkey_skin.png", "raw_donkey_meat.png"]
    elif mob_id == "mule":
        item_tex_patterns = ["mule_skin.png", "raw_mule_meat.png"]
    elif mob_id == "ocelot":
        item_tex_patterns = ["ocelot_skin.png", "raw_ocelot_meat.png", "cooked_ocelot_meat.png"]
    elif mob_id == "hoglin":
        item_tex_patterns = ["hoglin_skin.png", "hogling_chunk.png", "cooked_hogling_chunk.png"]
    elif mob_id == "zoglin":
        item_tex_patterns = ["zoglin_skin.png"]
    elif mob_id == "dolphin":
        item_tex_patterns = ["dolphin_skin.png", "dolphin_meat.png", "cooked_dolphin_meat.png"]
    
    for pattern in item_tex_patterns:
        src_file = tex_item_src / pattern
        if src_file.exists():
            dst_name = pattern
            if mob_id == "polar_bear":
                dst_name = dst_name.replace("polarbear", "polar_bear")
            elif mob_id == "hoglin":
                dst_name = dst_name.replace("hogling", "hoglin")
            elif mob_id == "donkey" and pattern == "donkey_skin.png":
                dst_name = f"{mob_id}_skin.png"
            elif mob_id == "mule" and pattern == "mule_skin.png":
                dst_name = f"{mob_id}_skin.png"
            dst_file = tex_item_dst / dst_name
            shutil.copy2(src_file, dst_file)
            print(f"    {dst_name}")
    
    # 7. LOOT TABLES
    print("  Loot tables...")
    loot_src = SRC_BASE / "data" / "butchery" / "loot_table" / "blocks"
    loot_dst = DST_BASE / "data" / "slaughter_hide" / "loot_table" / "blocks"
    
    loot_files = [
        f"{orig_prefix}_carcass.json",
        f"{orig_prefix}_carcass_loot.json",
        f"{orig_prefix}_cut_1_drop.json",
        f"{orig_prefix}_cut_2_drop.json",
        f"{orig_prefix}_cut_3_drop.json",
        f"{orig_prefix}_head.json",
        f"{orig_prefix}_head_drop.json",
        f"{orig_prefix}_head_mount.json",
        f"{orig_prefix}_skeleton.json",
        f"{orig_prefix}_skin_drop.json",
    ]
    
    # Special handling for polar_bear loot naming
    if mob_id == "polar_bear":
        loot_files = [
            "polar_bear_carcass.json",
            "polar_bear_carcass_loot.json",
            "polar_bear_carcass_cut_1_drop.json",
            "polar_bear_carcass_cut_2_drop.json",
            "polar_bear_carcass_cut_3_drop.json",
            "polar_bear_head.json",
            "polar_bear_head_drop.json",
            "polarbear_head_mount.json",
            "polarbear_skeleton.json",
            "polar_bear_skin_drop.json",
        ]
    elif mob_id == "panda":
        loot_files.append("panda_rug.json")
    elif mob_id == "polar_bear":
        loot_files.append("polarbear_rug.json")
    elif mob_id == "zoglin":
        # zoglin has no skeleton loot
        loot_files = [f for f in loot_files if "skeleton" not in f]
    
    for pattern in loot_files:
        src_file = loot_src / pattern
        if src_file.exists():
            # Determine target name
            dst_name = pattern
            if mob_id == "polar_bear":
                dst_name = dst_name.replace("polar_bear_carcass_cut", "polar_bear_cut")
                dst_name = dst_name.replace("polarbear_", "polar_bear_")
            dst_file = loot_dst / dst_name
            copy_file_with_remap(src_file, dst_file)
            print(f"    {dst_name}")
        else:
            # Try polarbear variant
            if mob_id == "polar_bear":
                alt = pattern.replace("polar_bear", "polarbear")
                src_file_alt = loot_src / alt
                if src_file_alt.exists():
                    dst_name = pattern.replace("polarbear", "polar_bear").replace("polar_bear_carcass_cut", "polar_bear_cut")
                    dst_file = loot_dst / dst_name
                    copy_file_with_remap(src_file_alt, dst_file)
                    print(f"    {dst_name} (from {alt})")
    
    print(f"  Done {mob_id}")

def main():
    print("Starting asset port for 9 mobs...")
    for mob_id, orig_prefix, has_skeleton in MOBS:
        port_mob(mob_id, orig_prefix, has_skeleton)
    print("\n=== All mobs ported ===")

if __name__ == "__main__":
    main()